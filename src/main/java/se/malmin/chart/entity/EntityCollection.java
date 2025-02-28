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
 * ---------------------
 * EntityCollection.java
 * ---------------------
 * (C) Copyright 2002-2020, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package se.malmin.chart.entity;

import java.util.Collection;
import java.util.Iterator;

/**
 * This interface defines the methods used to access an ordered list of
 * {@link ChartEntity} objects.
 */
public interface EntityCollection {

    /**
     * Clears all entities.
     */
    public void clear();

    /**
     * Adds an entity to the collection.
     *
     * @param entity  the entity ({@code null} not permitted).
     */
    public void add(ChartEntity entity);

    /**
     * Adds the entities from another collection to this collection.
     *
     * @param collection  the other collection.
     */
    public void addAll(EntityCollection collection);

    /**
     * Returns an entity whose area contains the specified point.
     *
     * @param x  the x coordinate.
     * @param y  the y coordinate.
     *
     * @return The entity.
     */
    public ChartEntity getEntity(double x, double y);

    /**
     * Returns an entity from the collection.
     *
     * @param index  the index (zero-based).
     *
     * @return An entity.
     */
    public ChartEntity getEntity(int index);

    /**
     * Returns the entity count.
     *
     * @return The entity count.
     */
    public int getEntityCount();

    /**
     * Returns the entities in an unmodifiable collection.
     *
     * @return The entities.
     */
    public Collection getEntities();

    /**
     * Returns an iterator for the entities in the collection.
     *
     * @return An iterator.
     */
    public Iterator iterator();

}
