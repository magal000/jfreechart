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
 * --------------------------------------------
 * DefaultBoxAndWhiskerCategoryDatasetTest.java
 * --------------------------------------------
 * (C) Copyright 2004-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 01-Mar-2004 : Version 1 (DG);
 * 17-Apr-2007 : Added a test for bug 1701822 (DG);
 * 28-Sep-2007 : Enhanced testClone() (DG);
 * 02-Oct-2007 : Added new tests (DG);
 * 03-Oct-2007 : Added getTestRangeBounds() and testRemove() (DG);
 *
 */

package org.jfree.data.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.data.Range;
import se.malmin.data.UnknownKeyException;
import se.malmin.data.statistics.BoxAndWhiskerItem;
import se.malmin.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

/**
 * Tests for the {@link DefaultBoxAndWhiskerCategoryDataset} class.
 */
public class DefaultBoxAndWhiskerCategoryDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultBoxAndWhiskerCategoryDataset d1
                = new DefaultBoxAndWhiskerCategoryDataset();
        d1.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "ROW1", "COLUMN1");
        DefaultBoxAndWhiskerCategoryDataset d2
                = new DefaultBoxAndWhiskerCategoryDataset();
        d2.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "ROW1", "COLUMN1");
        assertTrue(d1.equals(d2));
        assertTrue(d2.equals(d1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultBoxAndWhiskerCategoryDataset d1
                = new DefaultBoxAndWhiskerCategoryDataset();
        d1.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "ROW1", "COLUMN1");
        DefaultBoxAndWhiskerCategoryDataset d2 = 
                (DefaultBoxAndWhiskerCategoryDataset) TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     * 
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultBoxAndWhiskerCategoryDataset d1
                = new DefaultBoxAndWhiskerCategoryDataset();
        d1.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "ROW1", "COLUMN1");
        DefaultBoxAndWhiskerCategoryDataset d2 
                = (DefaultBoxAndWhiskerCategoryDataset) d1.clone();
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));

        // test independence
        d1.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "ROW2", "COLUMN1");
        assertFalse(d1.equals(d2));
    }

    /**
     * A simple test for bug report 1701822.
     */
    @Test
    public void test1701822() {
        DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        try {
            dataset.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 
                    null, 8.0, new ArrayList<Double>()), "ROW1", "COLUMN1");
            dataset.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0,
                    null, new ArrayList<Double>()), "ROW1", "COLUMN2");
        }
        catch (NullPointerException e) {
            assertTrue(false);
        }

    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the add() method.
     */
    @Test
    public void testAdd() {
        DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        BoxAndWhiskerItem item1 = new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0, new ArrayList<Double>());
        dataset.add(item1, "R1", "C1");

        assertEquals(2.0, dataset.getValue("R1", "C1").doubleValue(), EPSILON);
        assertEquals(1.0, dataset.getMeanValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(2.0, dataset.getMedianValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(3.0, dataset.getQ1Value("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(4.0, dataset.getQ3Value("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(5.0, dataset.getMinRegularValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(6.0, dataset.getMaxRegularValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(7.0, dataset.getMinOutlier("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(8.0, dataset.getMaxOutlier("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(new Range(7.0, 8.0), dataset.getRangeBounds(false));
    }

    /**
     * Some checks for the add() method.
     */
    @Test
    public void testAddUpdatesCachedRange() {
        DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        BoxAndWhiskerItem item1 = new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0, new ArrayList<Double>());
        dataset.add(item1, "R1", "C1");

        // now overwrite this item with another
        BoxAndWhiskerItem item2 = new BoxAndWhiskerItem(1.5, 2.5, 3.5, 4.5,
                5.5, 6.5, 7.5, 8.5, new ArrayList<Double>());
        dataset.add(item2, "R1", "C1");

        assertEquals(2.5, dataset.getValue("R1", "C1").doubleValue(), EPSILON);
        assertEquals(1.5, dataset.getMeanValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(2.5, dataset.getMedianValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(3.5, dataset.getQ1Value("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(4.5, dataset.getQ3Value("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(5.5, dataset.getMinRegularValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(6.5, dataset.getMaxRegularValue("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(7.5, dataset.getMinOutlier("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(8.5, dataset.getMaxOutlier("R1", "C1").doubleValue(),
                EPSILON);
        assertEquals(new Range(7.5, 8.5), dataset.getRangeBounds(false));
    }

    /**
     * Some basic checks for the constructor.
     */
    @Test
    public void testConstructor() {
        DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        assertEquals(0, dataset.getColumnCount());
        assertEquals(0, dataset.getRowCount());
        assertTrue(Double.isNaN(dataset.getRangeLowerBound(false)));
        assertTrue(Double.isNaN(dataset.getRangeUpperBound(false)));
    }

    /**
     * Some checks for the getRangeBounds() method.
     */
    @Test
    public void testGetRangeBounds() {
        DefaultBoxAndWhiskerCategoryDataset d1
                = new DefaultBoxAndWhiskerCategoryDataset();
        d1.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "R1", "C1");
        assertEquals(new Range(7.0, 8.0), d1.getRangeBounds(false));
        assertEquals(new Range(7.0, 8.0), d1.getRangeBounds(true));

        d1.add(new BoxAndWhiskerItem(1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5,
                new ArrayList<Double>()), "R1", "C1");
        assertEquals(new Range(7.5, 8.5), d1.getRangeBounds(false));
        assertEquals(new Range(7.5, 8.5), d1.getRangeBounds(true));

        d1.add(new BoxAndWhiskerItem(2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5,
                new ArrayList<Double>()), "R2", "C1");
        assertEquals(new Range(7.5, 9.5), d1.getRangeBounds(false));
        assertEquals(new Range(7.5, 9.5), d1.getRangeBounds(true));

        // this replaces the entry with the current minimum value, but the new
        // minimum value is now in a different item
        d1.add(new BoxAndWhiskerItem(1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 8.6, 9.6,
                new ArrayList<Double>()), "R1", "C1");
        assertEquals(new Range(8.5, 9.6), d1.getRangeBounds(false));
        assertEquals(new Range(8.5, 9.6), d1.getRangeBounds(true));
    }

    /**
     * Some checks for the remove method.
     */
    @Test
    public void testRemove() {
        DefaultBoxAndWhiskerCategoryDataset data
                = new DefaultBoxAndWhiskerCategoryDataset();

        boolean pass = false;
        try {
            data.remove("R1", "R2");
        }
        catch (UnknownKeyException e) {
            pass = true;
        }
        assertTrue(pass);
        data.add(new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                new ArrayList<Double>()), "R1", "C1");
        assertEquals(new Range(7.0, 8.0), data.getRangeBounds(false));
        assertEquals(new Range(7.0, 8.0), data.getRangeBounds(true));

        data.add(new BoxAndWhiskerItem(2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5,
                new ArrayList<Double>()), "R2", "C1");
        assertEquals(new Range(7.0, 9.5), data.getRangeBounds(false));
        assertEquals(new Range(7.0, 9.5), data.getRangeBounds(true));

        data.remove("R1", "C1");
        assertEquals(new Range(8.5, 9.5), data.getRangeBounds(false));
        assertEquals(new Range(8.5, 9.5), data.getRangeBounds(true));
    }

}
