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
 * --------------------------------------
 * DefaultBoxAndWhiskerXYDatasetTest.java
 * --------------------------------------
 * (C) Copyright 2007-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 12-Nov-2007 : Version 1 (DG);
 *
 */

package org.jfree.data.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.data.Range;
import se.malmin.data.statistics.BoxAndWhiskerItem;
import se.malmin.data.statistics.DefaultBoxAndWhiskerXYDataset;

/**
 * Tests for the {@link DefaultBoxAndWhiskerXYDataset} class.
 */
public class DefaultBoxAndWhiskerXYDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultBoxAndWhiskerXYDataset d1 = new DefaultBoxAndWhiskerXYDataset(
                "Series");
        DefaultBoxAndWhiskerXYDataset d2 = new DefaultBoxAndWhiskerXYDataset(
                "Series");
        assertTrue(d1.equals(d2));

        d1.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        assertFalse(d1.equals(d2));
        d2.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        assertTrue(d1.equals(d2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultBoxAndWhiskerXYDataset d1 = new DefaultBoxAndWhiskerXYDataset(
                "Series");
        d1.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        DefaultBoxAndWhiskerXYDataset d2 = (DefaultBoxAndWhiskerXYDataset) 
                TestUtils.serialised(d1);
        assertEquals(d1, d2);

        // test independence
        d1.add(new Date(2L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        assertFalse(d1.equals(d2));
    }

    /**
     * Confirm that cloning works.
     * 
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultBoxAndWhiskerXYDataset d1 = new DefaultBoxAndWhiskerXYDataset(
                "Series");
        d1.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        DefaultBoxAndWhiskerXYDataset d2 = (DefaultBoxAndWhiskerXYDataset) 
                d1.clone();
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));

        // test independence
        d1.add(new Date(2L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        assertFalse(d1.equals(d2));
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the add() method.
     */
    @Test
    public void testAdd() {
        DefaultBoxAndWhiskerXYDataset dataset
                = new DefaultBoxAndWhiskerXYDataset("S1");
        BoxAndWhiskerItem item1 = new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0, new ArrayList<Double>());
        dataset.add(new Date(33L), item1);

        assertEquals(1.0, dataset.getY(0, 0).doubleValue(), EPSILON);
        assertEquals(1.0, dataset.getMeanValue(0, 0).doubleValue(), EPSILON);
        assertEquals(2.0, dataset.getMedianValue(0, 0).doubleValue(), EPSILON);
        assertEquals(3.0, dataset.getQ1Value(0, 0).doubleValue(), EPSILON);
        assertEquals(4.0, dataset.getQ3Value(0, 0).doubleValue(), EPSILON);
        assertEquals(5.0, dataset.getMinRegularValue(0, 0).doubleValue(),
                EPSILON);
        assertEquals(6.0, dataset.getMaxRegularValue(0, 0).doubleValue(),
                EPSILON);
        assertEquals(7.0, dataset.getMinOutlier(0, 0).doubleValue(), EPSILON);
        assertEquals(8.0, dataset.getMaxOutlier(0, 0).doubleValue(), EPSILON);
        assertEquals(new Range(5.0, 6.0), dataset.getRangeBounds(false));
    }

    /**
     * Some basic checks for the constructor.
     */
    @Test
    public void testConstructor() {
        DefaultBoxAndWhiskerXYDataset dataset
                = new DefaultBoxAndWhiskerXYDataset("S1");
        assertEquals(1, dataset.getSeriesCount());
        assertEquals(0, dataset.getItemCount(0));
        assertTrue(Double.isNaN(dataset.getRangeLowerBound(false)));
        assertTrue(Double.isNaN(dataset.getRangeUpperBound(false)));
    }

    /**
     * Some checks for the getRangeBounds() method.
     */
    @Test
    public void testGetRangeBounds() {
        DefaultBoxAndWhiskerXYDataset d1
                = new DefaultBoxAndWhiskerXYDataset("S");
        d1.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, new ArrayList<Double>()));
        assertEquals(new Range(5.0, 6.0), d1.getRangeBounds(false));
        assertEquals(new Range(5.0, 6.0), d1.getRangeBounds(true));

        d1.add(new Date(1L), new BoxAndWhiskerItem(1.5, 2.5, 3.5, 4.5, 5.5,
                6.5, 7.5, 8.5, new ArrayList<Double>()));
        assertEquals(new Range(5.0, 6.5), d1.getRangeBounds(false));
        assertEquals(new Range(5.0, 6.5), d1.getRangeBounds(true));

        d1.add(new Date(2L), new BoxAndWhiskerItem(2.5, 3.5, 4.5, 5.5, 6.5,
                7.5, 8.5, 9.5, new ArrayList()));
        assertEquals(new Range(5.0, 7.5), d1.getRangeBounds(false));
        assertEquals(new Range(5.0, 7.5), d1.getRangeBounds(true));
    }

}
