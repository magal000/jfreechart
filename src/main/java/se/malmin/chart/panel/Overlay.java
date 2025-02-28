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
 * ------------
 * Overlay.java
 * ------------
 * (C) Copyright 2009-2021, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */
package se.malmin.chart.panel;

import java.awt.Graphics2D;

import se.malmin.chart.ChartPanel;
import se.malmin.chart.event.OverlayChangeListener;

/**
 * Defines the interface for an overlay that can be added to a
 * {@link ChartPanel}.
 */
public interface Overlay {

    /**
     * Paints the crosshairs in the layer.
     *
     * @param g2  the graphics target.
     * @param chartPanel  the chart panel.
     */
    public void paintOverlay(Graphics2D g2, ChartPanel chartPanel);

    /**
     * Registers a change listener with the overlay.
     * 
     * @param listener  the listener.
     */
    public void addChangeListener(OverlayChangeListener listener);

    /**
     * Deregisters a listener from the overlay.
     * 
     * @param listener  the listener.
     */
    public void removeChangeListener(OverlayChangeListener listener);

}
