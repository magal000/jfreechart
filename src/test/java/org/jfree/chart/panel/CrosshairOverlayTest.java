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
 * -------------------------
 * CrosshairOverlayTest.java
 * -------------------------
 * (C) Copyright 2009-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 10-Apr-2009 : Version 1 (DG);
 *
 */

package org.jfree.chart.panel;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.chart.panel.CrosshairOverlay;
import se.malmin.chart.plot.Crosshair;

/**
 * Tests for the {@link CrosshairOverlay} class.
 */
public class CrosshairOverlayTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        CrosshairOverlay o1 = new CrosshairOverlay();
        CrosshairOverlay o2 = new CrosshairOverlay();
        assertEquals(o1, o2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CrosshairOverlay o1 = new CrosshairOverlay();
        o1.addDomainCrosshair(new Crosshair(99.9));
        o1.addRangeCrosshair(new Crosshair(1.23, new GradientPaint(1.0f, 2.0f,
                Color.RED, 3.0f, 4.0f, Color.BLUE), new BasicStroke(1.1f)));
        CrosshairOverlay o2 = (CrosshairOverlay) TestUtils.serialised(o1);
        assertEquals(o1, o2);
    }

    /**
     * Basic checks for cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CrosshairOverlay o1 = new CrosshairOverlay();
        o1.addDomainCrosshair(new Crosshair(99.9));
        o1.addRangeCrosshair(new Crosshair(1.23, new GradientPaint(1.0f, 2.0f,
                Color.RED, 3.0f, 4.0f, Color.BLUE), new BasicStroke(1.1f)));
        CrosshairOverlay o2 = (CrosshairOverlay) o1.clone();
        assertTrue(o1 != o2);
        assertTrue(o1.getClass() == o2.getClass());
        assertTrue(o1.equals(o2));

        o1.addDomainCrosshair(new Crosshair(3.21));
        o1.addRangeCrosshair(new Crosshair(4.32));
        assertFalse(o1.equals(o2));
    }

}
