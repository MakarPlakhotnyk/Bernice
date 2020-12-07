package plItem;

import arrow.Arrow;
import java.awt.Point;

/** Is Item of a Diagram
 *
 * @author Makar Plakhotnyk
 */
public interface IsDItem {

    public Point getCener();
    public Point getToCenter(Point p);

    public void add(Arrow arrow);
    public int getId();
    public boolean hasId(int fndId);
    public boolean isActive();
    public void remove(Arrow arrow);

    public Point getTop();

    public Point getLeft();

    public Point getRight();

    public Point getBottom();

    public Point getVert(Point fCenter);

    public Point getHoriz(Point fCenter);

    public Point getClosest(Point fCenter);
}
