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
 * ------------------------------------
 * SlidingGanttCategoryDatasetTest.java
 * ------------------------------------
 * (C) Copyright 2008-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 08-May-2008 : Version 1 (DG);
 *
 */

package org.jfree.data.gantt;

import java.util.Date;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.data.gantt.SlidingGanttCategoryDataset;
import se.malmin.data.gantt.Task;
import se.malmin.data.gantt.TaskSeries;
import se.malmin.data.gantt.TaskSeriesCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link SlidingGanttCategoryDataset} class.
 */
public class SlidingGanttCategoryDatasetTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        TaskSeries s1 = new TaskSeries("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        s1.add(new Task("Task 3", new Date(20L), new Date(21L)));
        TaskSeriesCollection u1 = new TaskSeriesCollection();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        TaskSeries s2 = new TaskSeries("Series");
        s2.add(new Task("Task 1", new Date(0L), new Date(1L)));
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        s2.add(new Task("Task 3", new Date(20L), new Date(21L)));
        TaskSeriesCollection u2 = new TaskSeriesCollection();
        u2.add(s2);
        SlidingGanttCategoryDataset d2 = new SlidingGanttCategoryDataset(
                u2, 0, 5);
        assertTrue(d1.equals(d2));

        d1.setFirstCategoryIndex(1);
        assertFalse(d1.equals(d2));
        d2.setFirstCategoryIndex(1);
        assertTrue(d1.equals(d2));

        d1.setMaximumCategoryCount(99);
        assertFalse(d1.equals(d2));
        d2.setMaximumCategoryCount(99);
        assertTrue(d1.equals(d2));

        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertFalse(d1.equals(d2));
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertTrue(d1.equals(d2));
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TaskSeries s1 = new TaskSeries("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        TaskSeriesCollection u1 = new TaskSeriesCollection();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        SlidingGanttCategoryDataset d2 = (SlidingGanttCategoryDataset) 
                d1.clone();
        assertTrue(d1 != d2);
        assertTrue(d1.getClass() == d2.getClass());
        assertTrue(d1.equals(d2));

        // basic check for independence
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertFalse(d1.equals(d2));
        TaskSeriesCollection u2
                = (TaskSeriesCollection) d2.getUnderlyingDataset();
        TaskSeries s2 = u2.getSeries("Series");
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertTrue(d1.equals(d2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TaskSeries s1 = new TaskSeries("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        TaskSeriesCollection u1 = new TaskSeriesCollection();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        SlidingGanttCategoryDataset d2 = (SlidingGanttCategoryDataset) 
                TestUtils.serialised(d1);
        assertEquals(d1, d2);

        // basic check for independence
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertFalse(d1.equals(d2));
        TaskSeriesCollection u2
                = (TaskSeriesCollection) d2.getUnderlyingDataset();
        TaskSeries s2 = u2.getSeries("Series");
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertTrue(d1.equals(d2));
    }

}
