package geometrayshape;

import shape.Circle;
import shape.Rectangle;
import shape.Triangle;

public class GeometrayShape {

    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        
        System.out.println("Circle Perimeter: " + circle.getPerimeter());
        System.out.println("Circle Area: " + circle.getArea());
    
        Rectangle rectangle= new Rectangle(4.0, 6.0);
        
        System.out.println("Rectangle Perimeter: " + rectangle.getPerimeter());
        System.out.println("Rectangle Area: " + rectangle.getArea());
        
        Triangle triangle=new Triangle(3.0, 4.0, 5.0);
        
        System.out.println("Triangle Perimeter: " + triangle.getPerimeter());
        System.out.println("Triangle Area: "+ triangle.getArea());  
        
    }
    
    
    

}
