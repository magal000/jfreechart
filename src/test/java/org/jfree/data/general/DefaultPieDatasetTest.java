/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2021, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -------------------
 * PieDatasetTest.java
 * -------------------
 * (C) Copyright 2003-2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.data.general;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.data.general.DatasetChangeEvent;
import se.malmin.data.general.DatasetChangeListener;
import se.malmin.data.general.DefaultPieDataset;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link se.malmin.data.general.PieDataset} class.
 */
public class DefaultPieDatasetTest implements DatasetChangeListener {

    private DatasetChangeEvent lastEvent;

    /**
     * Records the last event.
     *
     * @param event  the last event.
     */
    @Override
    public void datasetChanged(DatasetChangeEvent event) {
        this.lastEvent = event;
    }

    /**
     * Some tests for the clear() method.
     */
    @Test
    public void testClear() {
        DefaultPieDataset d = new DefaultPieDataset();
        d.addChangeListener(this);
        // no event is generated if the dataset is already empty
        d.clear();
        assertNull(this.lastEvent);
        d.setValue("A", 1.0);
        assertEquals(1, d.getItemCount());
        this.lastEvent = null;
        d.clear();
        assertNotNull(this.lastEvent);
        assertEquals(0, d.getItemCount());
    }

    /**
     * Some checks for the getKey(int) method.
     */
    @Test
    public void testGetKey() {
        DefaultPieDataset d = new DefaultPieDataset();
        d.setValue("A", 1.0);
        d.setValue("B", 2.0);
        assertEquals("A", d.getKey(0));
        assertEquals("B", d.getKey(1));

        boolean pass = false;
        try {
            d.getKey(-1);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            d.getKey(2);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Some checks for the getIndex() method.
     */
    @Test
    public void testGetIndex() {
        DefaultPieDataset d = new DefaultPieDataset();
        d.setValue("A", 1.0);
        d.setValue("B", 2.0);
        assertEquals(0, d.getIndex("A"));
        assertEquals(1, d.getIndex("B"));
        assertEquals(-1, d.getIndex("XX"));

        boolean pass = false;
        try {
            d.getIndex(null);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Confirm that cloning works.
     * 
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultPieDataset d1 = new DefaultPieDataset();
        d1.setValue("V1", 1);
        d1.setValue("V2", null);
        d1.setValue("V3", 3);
        DefaultPieDataset d2 = (DefaultPieDataset) d1.clone();

        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultPieDataset d1 = new DefaultPieDataset();
        d1.setValue("C1", 234.2);
        d1.setValue("C2", null);
        d1.setValue("C3", 345.9);
        d1.setValue("C4", 452.7);

        DefaultPieDataset d2 = (DefaultPieDataset) TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

    /**
     * A test for bug report https://github.com/jfree/jfreechart/issues/212
     */
    @Test
    public void testBug212() {
        DefaultPieDataset d = new DefaultPieDataset();
        assertThrows(IndexOutOfBoundsException.class, () ->  d.getValue(-1));
        assertThrows(IndexOutOfBoundsException.class, () ->  d.getValue(0));
        d.setValue("A", 1.0);
        assertEquals(1.0, d.getValue(0));
        assertThrows(IndexOutOfBoundsException.class, () ->  d.getValue(1));        
    }
}
