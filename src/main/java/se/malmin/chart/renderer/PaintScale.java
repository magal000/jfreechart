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
 * PaintScale.java
 * ---------------
 * (C) Copyright 2006-2021, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package se.malmin.chart.renderer;

import java.awt.Paint;

import se.malmin.chart.renderer.xy.XYBlockRenderer;

/**
 * A source for {@code Paint} instances, used by the
 * {@link XYBlockRenderer}.
 * <br><br>
 * NOTE: Classes that implement this interface should also implement
 * {@code PublicCloneable} and {@code Serializable}, so
 * that any renderer (or other object instance) that references an instance of
 * this interface can still be cloned or serialized.
 */
public interface PaintScale {

    /**
     * Returns the lower bound for the scale.
     *
     * @return The lower bound.
     *
     * @see #getUpperBound()
     */
    public double getLowerBound();

    /**
     * Returns the upper bound for the scale.
     *
     * @return The upper bound.
     *
     * @see #getLowerBound()
     */
    public double getUpperBound();

    /**
     * Returns a {@code Paint} instance for the specified value.
     *
     * @param value  the value.
     *
     * @return A {@code Paint} instance (never {@code null}).
     */
    public Paint getPaint(double value);

}
