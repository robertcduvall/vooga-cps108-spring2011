/*
 * Copyright (c) 2008 Golden T Studios.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package vooga.sprites.spritegroups;

// JFC
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import vooga.sprites.improvedsprites.BaseSprite;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.Utility;

/**
 * Group of sprites with common behaviour, for example PLAYER_GROUP,
 * ENEMY_GROUP, etc. This class maintain a growable sprite list (array of
 * sprites). Each time a sprite is added into this group, this group
 * automatically adjust the size of its sprites array.
 * <p>
 * 
 * <code>SpriteGroup</code> is used to store a list of sprites and also manage
 * the sprites updating, rendering, and collision check.
 * <p>
 * 
 * For example how to create and use sprite group :
 * 
 * <pre>
 * SpriteGroup ENEMY_GROUP;
 * 
 * public void initResources() {
 *  // creates the enemy sprites
 *  Sprite asteroid = new Sprite();
 *  Sprite asteroid2 = new Sprite();
 *  // creates and add the sprites into enemy group
 *  ENEMY_GROUP = new SpriteGroup(&quot;Enemy&quot;);
 *  ENEMY_GROUP.add(asteroid);
 *  ENEMY_GROUP.add(asteroid2);
 * }
 * 
 * public void update(long elapsedTime) {
 *  // update all enemies at once
 *  ENEMY_GROUP.update(elapsedTime);
 * }
 * 
 * public void render(Graphics2D g) {
 *  // render all enemies at once to the screen
 *  ENEMY_GROUP.render(g);
 * }
 * </pre>
 * 
 * @see com.golden.gamedev.object.PlayField
 * @see com.golden.gamedev.object.collision.CollisionGroup
 */
public class SpriteGroup<T extends Sprite> extends ArrayList<T>{
    
    /** *********************** GROUP SPRITE FACTOR ***************************** */
    
    // removes inactive sprites every 15 seconds
    private Timer scanFrequence = new Timer(15000);
    
    /** ************************ GROUP PROPERTIES ******************************* */
    
    private String name; // group name (for identifier only)
    private boolean active = true;
    
    private Background background;
    
    private Comparator comparator; // comparator for sorting sprite
    
    /** ****************** SPRITES THAT BELONG TO THIS GROUP ******************** */
    
    
    /** ************************************************************************* */
    /** ************************** CONSTRUCTOR ********************************** */
    /** ************************************************************************* */
    
    /**
     * Creates a new sprite group, with specified name. Name is used for group
     * identifier only.
     */
    @SuppressWarnings("unchecked")
    public SpriteGroup(String name, T ... sprites) {
        this.name = name;
        this.background = Background.getDefaultBackground();
        this.addAll(Arrays.asList(sprites));
    }
    
   
    
    
    
 
    /** ************************************************************************* */
    /** ************************* UPDATE THIS GROUP ***************************** */
    /** ************************************************************************* */
    
    /**
     * Updates all active sprites in this group, and check the schedule for
     * removing inactive sprites.
     * 
     * @see #getScanFrequence()
     */
    @SuppressWarnings("unchecked")
    public void update(long elapsedTime) {
        for (T sprite: this) {
            if (sprite.isActive()) {
                sprite.update(elapsedTime);
            }
        }
        
        if (this.scanFrequence.action(elapsedTime)) {
            // remove all inactive sprites
            this.removeInactiveSprites();
        }
    }
    
    /**
     * Throws any inactive sprites from this group, this method won't remove
     * immutable sprites, to remove all inactive sprites even though the
     * inactive sprites are immutable use {@link #removeImmutableSprites()}.
     * <p>
     * 
     * This method is automatically called every time
     * {@linkplain #getScanFrequence() timer for scanning inactive sprite} is
     * scheduled.
     * 
     * @see #getScanFrequence()
     * @see #removeImmutableSprites()
     * @see com.golden.gamedev.object.Sprite#setImmutable(boolean)
     */
    public void removeInactiveSprites() {
        this.removeSprites(false);
    }
    
    /**
     * Throws all inactive sprites from this group even the sprite is
     * {@link com.golden.gamedev.object.Sprite#setImmutable(boolean) immutable
     * sprite}.
     * 
     * @see #getInactiveSprite()
     * @see #removeInactiveSprites()
     * @see com.golden.gamedev.object.Sprite#setImmutable(boolean)
     */
    public void removeImmutableSprites() {
        this.removeSprites(true);
    }
    
    private void removeSprites(boolean removeImmutable) {
        ArrayList<T> temp = new ArrayList<T>();
        for (T sprite: this) {
            if (!sprite.isActive()) {
                if (!sprite.isImmutable() || removeImmutable == true){
                    temp.add(sprite);
                }
            }
                
            
        }
        this.removeAll(temp);
        
    }
    
    /** ************************************************************************* */
    /** ******************** RENDER TO GRAPHICS CONTEXT ************************* */
    /** ************************************************************************* */
    
    /**
     * Renders all active sprites in this group. If this group is associated
     * with a comparator, the group sprites is sort against the comparator first
     * before rendered.
     * 
     * @see #setComparator(Comparator)
     */
    public void render(Graphics2D g) {
        if (this.comparator != null) {
            // sort sprite before render
            this.sort(this.comparator);
        }
        
        for (T sprite : this) {
            if (sprite.isActive()) {
                // renders only active sprite
                sprite.render(g);
            }
        }
    }
    
    /**
     * Sorts all sprites in this group with specified comparator.
     * <p>
     * 
     * This method only sort the sprites once called, use
     * {@link #setComparator(Comparator)} instead to sort the sprites on each
     * update.
     * 
     * @see #setComparator(Comparator)
     */
    public void sort(Comparator<T> c) {
        Collections.sort(this, c);
    }
    
    /** ************************************************************************* */
    /** ************************** GROUP PROPERTIES ***************************** */
    /** ************************************************************************* */
    
    /**
     * Returns the name of this group. Name is used for group identifier only.
     * 
     * @see #setName(String)
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the name of this group. Name is used for group identifier only.
     * 
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the background of this group.
     * 
     * @see #setBackground(Background)
     */
    public Background getBackground() {
        return this.background;
    }
    
    /**
     * Associates specified background with this sprite group, the background
     * will be used by all sprites in this group.
     * 
     * @see #getBackground()
     */
    public void setBackground(Background backgr) {
        this.background = backgr;
        if (this.background == null) {
            this.background = Background.getDefaultBackground();
        }
        
        // force all sprites to use a same background
        for (T sprite: this) {
            sprite.setBackground(this.background);
        }
    }
    
    /**
     * Returns active state of this group.
     * 
     * @see #setActive(boolean)
     */
    public boolean isActive() {
        return this.active;
    }
    
    /**
     * Sets active state of this group, inactive group won't be updated,
     * rendered, and won't be checked for collision.
     * <p>
     * 
     * @see #isActive()
     */
    public void setActive(boolean b) {
        this.active = b;
    }
    
    /**
     * Returns comparator used for sorting sprites in this group.
     * 
     * @see #setComparator(Comparator)
     */
    public Comparator getComparator() {
        return this.comparator;
    }
    
    /**
     * Sets comparator used for sorting sprites in this group. Specify null
     * comparator for unsort order (the first sprite in the array will be
     * rendered at the back of other sprite).
     * <p>
     * 
     * The comparator is used by
     * {@link java.util.Arrays#sort(java.lang.Object[], int, int, java.util.Comparator)}
     * to sort the sprites before rendering. For more information about how to
     * make comparator, please read java.util.Comparator and
     * java.util.Arrays#sort().
     * 
     * Example of sorting sprites based on y-axis :
     * 
     * <pre>
     *    SpriteGroup ENEMY_GROUP;
     *    ENEMY_GROUP.setComparator(
     *       new Comparator() {
     *          public int compare(Object o1, Object o2) {
     *             Sprite s1 = (Sprite) o1,
     *                    s2 = (Sprite) o2;
     *             return (s1.getY() - s2.getY());
     *          }
     *       }
     *    };
     * </pre>
     * 
     * @param c the sprite comparator, null for unsort order
     * @see java.util.Comparator
     * @see java.util.Arrays#sort(java.lang.Object[], int, int,
     *      java.util.Comparator)
     */
    public void setComparator(Comparator c) {
        this.comparator = c;
    }
    
    /** ************************************************************************* */
    /** *************************** SPRITES GETTER ****************************** */
    /** ************************************************************************* */
    
    /**
     * Returns the first active sprite found in this group, or null if there is
     * no active sprite.
     * <p>
     * 
     * This method usually used to check whether this group still have alive
     * member or not. <br>
     * Note: alive group has different meaning from
     * {@linkplain #setActive(boolean) active} group, inactive group is not
     * updated and rendered even there are many active sprites in the group, but
     * dead group means there are no active sprites in the group.
     * <p>
     * 
     * For example :
     * 
     * <pre>
     * SpriteGroup ENEMY_GROUP;
     * if (ENEMY_GROUP.getActiveSprite() == null) {
     *  // no active enemy, advance to next level
     *  gameState = WIN;
     * }
     * </pre>
     * 
     * @return The first found active sprite, or null if there is no active
     *         sprite in this group.
     * @see com.golden.gamedev.object.Sprite#setActive(boolean)
     */
    public T getActiveSprite() {
        for (T sprite: this) {
            if (sprite.isActive()) {
                return (T) sprite;
            }
        }
        
        return null;
    }
    
    /**
     * Returns the first inactive sprite found in this group (the returned
     * sprite is automatically set to active), or null if there is no inactive
     * sprite, please see
     * {@link com.golden.gamedev.object.Sprite#setImmutable(boolean)} for tag
     * method of this method.
     * <p>
     * 
     * This method is used for optimization, to reuse inactive sprite instead of
     * making new sprite.
     * <p>
     * 
     * For example :
     * 
     * <pre>
     *    SpriteGroup PROJECTILE_GROUP;
     *    Sprite projectile = PROJECTILE_GROUP.getInactiveSprite();
     *    if (projectile == null) {
     *       // create new projectile if there is no inactive projectile
     *       projectile = new Sprite(...);
     *       projectile.setImmutable(true);
     *       PROJECTILE_GROUP.add(projectile);
     *    }
     *    // set projectile location and other stuff
     *    projectile.setLocation(....);
     * </pre>
     * 
     * <p>
     * 
     * This method is only a convenient way to return the first found inactive
     * sprite. To filter the inactive sprite, simply find and then filter the
     * inactive sprite like this :
     * 
     * <pre>
     *    SpriteGroup A_GROUP;
     *    Sprite inactiveSprite = null;
     *    Sprite[] sprites = A_GROUP.getSprites();
     *    int size = A_GROUP.getSize();
     *    for (int i=0;i &lt; size;i++) {
     *       if (!sprites[i].isActive()) {
     *          // do the filter
     *          // for example, we want only reuse sprite that has ID = 100
     *          if (sprites[i].getID() == 100) {
     *             inactiveSprite = sprites[i];
     *             // activate sprite
     *             inactiveSprite.setActive(true);
     *             break;
     *          }
     *       }
     *    }
     *    if (inactiveSprite == null) {
     *       // no inactive sprite found like the criteria (ID = 100)
     *       // create new sprite
     *    }
     *    // reuse the inactive sprite
     *    inactiveSprite.setLocation(...);
     * </pre>
     * 
     * 
     * @return The first found inactive sprite, or null if there is no inactive
     *         sprite in this group.
     * @see com.golden.gamedev.object.Sprite#setImmutable(boolean)
     */
    public Sprite getInactiveSprite() {
        for (T sprite: this) {
            if (!sprite.isActive()) {
                sprite.setActive(true);
                return sprite;
            }
        }
        
        return null;
    }
    
  

    
    
    /**
     * Schedule timer for {@linkplain #removeInactiveSprites() removing inactive
     * sprites}.
     * <p>
     * 
     * For example to set this group to scan inactive sprite every 30 seconds
     * (the default is 15 seconds) :
     * 
     * <pre>
     * SpriteGroup PLAYER_GROUP;
     * PLAYER_GROUP.getScanFrequence().setDelay(30000); // 30 x 1000 ms
     * </pre>
     * 
     * @see #removeInactiveSprites()
     */
    public Timer getScanFrequence() {
        return this.scanFrequence;
    }
    
    public String toString() {
        return super.toString() + " " + "[name=" + this.name + ", active="
                + this.size() + ", total="
                + ", member=" + this.getActiveSprite() + ", background="
                + this.background + "]";
    }
    
}
