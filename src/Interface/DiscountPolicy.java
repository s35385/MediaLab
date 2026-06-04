package Interface;

import Model.Student;

public interface DiscountPolicy {
    double applyDiscount(Student student, double price);
}
