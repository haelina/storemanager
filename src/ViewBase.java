
/**
 * BaseClass for creating EmployeeView and CustomerView.
 */
public abstract class ViewBase {
    abstract void start();
    abstract void printOptions();
    abstract boolean validateOption(int option);
    abstract void taskDelegator(int option);
}
