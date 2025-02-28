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
 * ---------------
 * BarPainter.java
 * ---------------
 * (C) Copyright 2008-2021, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package se.malmin.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;

import se.malmin.chart.ui.RectangleEdge;

/**
 * The interface for plugin painter for the {@link BarRenderer} class.  When
 * developing a class that implements this interface, bear in mind the
 * following:
 * <ul>
 * <li>the {@code equals(Object)} method should be overridden;</li>
 * <li>instances of the class should be immutable OR implement the
 *     {@code PublicCloneable} interface, so that a renderer using the
 *     painter can be cloned reliably;
 * <li>the class should be {@code Serializable}, otherwise chart
 *     serialization will not be supported.</li>
 * </ul>
 */
public interface BarPainter {

    /**
     * Paints a single bar on behalf of a renderer.
     *
     * @param g2  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index for the item.
     * @param column  the column index for the item.
     * @param bar  the bounds for the bar.
     * @param base  the base of the bar.
     */
    public void paintBar(Graphics2D g2, BarRenderer renderer,
            int row, int column, RectangularShape bar, RectangleEdge base);

    /**
     * Paints the shadow for a single bar on behalf of a renderer.
     *
     * @param g2  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index for the item.
     * @param column  the column index for the item.
     * @param bar  the bounds for the bar.
     * @param base  the base of the bar.
     * @param pegShadow  peg the shadow to the base of the bar?
     */
    public void paintBarShadow(Graphics2D g2, BarRenderer renderer,
            int row, int column, RectangularShape bar, RectangleEdge base,
            boolean pegShadow);

}
