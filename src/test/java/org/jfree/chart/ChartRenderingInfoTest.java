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
 * ---------------------------
 * ChartRenderingInfoTest.java
 * ---------------------------
 * (C) Copyright 2004-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Mar-2004 : Version 1 (DG);
 * 30-Nov-2005 : Updated for removed field in ChartRenderingInfo (DG);
 *
 */

package org.jfree.chart;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import se.malmin.chart.ChartRenderingInfo;
import se.malmin.chart.entity.ChartEntity;
import se.malmin.chart.entity.StandardEntityCollection;

/**
 * Tests for the {@link ChartRenderingInfo} class.
 */
public class ChartRenderingInfoTest  {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        ChartRenderingInfo i2 = new ChartRenderingInfo();
        assertEquals(i1, i2);

        i1.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertFalse(i1.equals(i2));
        i2.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertEquals(i1, i2);

        i1.getPlotInfo().setDataArea(new Rectangle(1, 2, 3, 4));
        assertFalse(i1.equals(i2));
        i2.getPlotInfo().setDataArea(new Rectangle(1, 2, 3, 4));
        assertEquals(i1, i2);

        StandardEntityCollection e1 = new StandardEntityCollection();
        e1.add(new ChartEntity(new Rectangle(1, 2, 3, 4)));
        i1.setEntityCollection(e1);
        assertFalse(i1.equals(i2));
        StandardEntityCollection e2 = new StandardEntityCollection();
        e2.add(new ChartEntity(new Rectangle(1, 2, 3, 4)));
        i2.setEntityCollection(e2);
    }

    /**
     * Confirm that cloning works.
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        ChartRenderingInfo i2 = (ChartRenderingInfo) i1.clone();

        assertNotSame(i1, i2);
        assertSame(i1.getClass(), i2.getClass());
        assertEquals(i1, i2);

        // check independence
        i1.getChartArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertFalse(i1.equals(i2));
        i2.getChartArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertEquals(i1, i2);

        i1.getEntityCollection().add(new ChartEntity(new Rectangle(1, 2, 2,
                1)));
        assertFalse(i1.equals(i2));
        i2.getEntityCollection().add(new ChartEntity(new Rectangle(1, 2, 2,
                1)));
        assertEquals(i1, i2);

    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        i1.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        ChartRenderingInfo i2 = (ChartRenderingInfo) TestUtils.serialised(i1);
        assertEquals(i1, i2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization2() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        i1.getPlotInfo().setDataArea(new Rectangle2D.Double(1.0, 2.0, 3.0,
                4.0));
        ChartRenderingInfo i2 = (ChartRenderingInfo) TestUtils.serialised(i1);
        assertEquals(i1, i2);
        assertEquals(i2, i2.getPlotInfo().getOwner());
    }
}
