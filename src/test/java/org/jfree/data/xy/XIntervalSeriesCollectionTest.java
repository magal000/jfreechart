/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2020, by Object Refinery Limited and Contributors.
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
 * ----------------------------------
 * XIntervalSeriesCollectionTest.java
 * ----------------------------------
 * (C) Copyright 2006-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 * 18-Jan-2008 : Added testRemoveSeries() (DG);
 * 22-Apr-2008 : Added testPublicCloneable (DG);
 *
 */

package org.jfree.data.xy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.chart.util.PublicCloneable;
import se.malmin.data.xy.XIntervalSeries;
import se.malmin.data.xy.XIntervalSeriesCollection;

/**
 * Tests for the {@link XIntervalSeriesCollection} class.
 */
public class XIntervalSeriesCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XIntervalSeriesCollection c1 = new XIntervalSeriesCollection();
        XIntervalSeriesCollection c2 = new XIntervalSeriesCollection();
        assertEquals(c1, c2);

        // add a series
        XIntervalSeries s1 = new XIntervalSeries("Series");
        s1.add(1.0, 1.1, 1.2, 1.3);
        c1.addSeries(s1);
        assertFalse(c1.equals(c2));
        XIntervalSeries s2 = new XIntervalSeries("Series");
        s2.add(1.0, 1.1, 1.2, 1.3);
        c2.addSeries(s2);
        assertTrue(c1.equals(c2));

        // add an empty series
        c1.addSeries(new XIntervalSeries("Empty Series"));
        assertFalse(c1.equals(c2));
        c2.addSeries(new XIntervalSeries("Empty Series"));
        assertTrue(c1.equals(c2));
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XIntervalSeriesCollection c1 = new XIntervalSeriesCollection();
        XIntervalSeries s1 = new XIntervalSeries("Series");
        s1.add(1.0, 1.1, 1.2, 1.3);
        c1.addSeries(s1);
        XIntervalSeriesCollection c2 = (XIntervalSeriesCollection) c1.clone();
        assertTrue(c1 != c2);
        assertTrue(c1.getClass() == c2.getClass());
        assertTrue(c1.equals(c2));

        // check independence
        s1.setDescription("XYZ");
        assertFalse(c1.equals(c2));
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XIntervalSeriesCollection c1 = new XIntervalSeriesCollection();
        assertTrue(c1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XIntervalSeriesCollection c1 = new XIntervalSeriesCollection();
        XIntervalSeries s1 = new XIntervalSeries("Series");
        s1.add(1.0, 1.1, 1.2, 1.3);
        XIntervalSeriesCollection c2 = (XIntervalSeriesCollection) 
                TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

    /**
     * Some basic checks for the removeSeries() method.
     */
    @Test
    public void testRemoveSeries() {
        XIntervalSeriesCollection c = new XIntervalSeriesCollection();
        XIntervalSeries s1 = new XIntervalSeries("s1");
        c.addSeries(s1);
        c.removeSeries(0);
        assertEquals(0, c.getSeriesCount());
        c.addSeries(s1);

        boolean pass = false;
        try {
            c.removeSeries(-1);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            c.removeSeries(1);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * A test for bug report 1170825 (originally affected XYSeriesCollection,
     * this test is just copied over).
     */
    @Test
    public void test1170825() {
        XIntervalSeries s1 = new XIntervalSeries("Series1");
        XIntervalSeriesCollection dataset = new XIntervalSeriesCollection();
        dataset.addSeries(s1);
        try {
            /* XYSeries s = */ dataset.getSeries(1);
        }
        catch (IllegalArgumentException e) {
            // correct outcome
        }
        catch (IndexOutOfBoundsException e) {
            assertTrue(false);  // wrong outcome
        }
    }

}
