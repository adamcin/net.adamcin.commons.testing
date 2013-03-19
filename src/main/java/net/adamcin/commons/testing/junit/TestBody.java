package net.adamcin.commons.testing.junit;


/**
 * Abstract TestBody class that makes it easier to avoid the catch-all exception
 * boilerplate that your IDE won't create, and replace it with boilerplate that it
 * will create, i.e. stubbing out implementations/overrides. Simple implementations
 * will likely be visually reduced by the IDE to something that looks like a function.
 *
 * This is especially useful as a safety net for uncaught NullPointerExceptions, which
 * would otherwise get swallowed by JUnit with no message and no line number. This
 * facility acts as a backstop such that any Exception that is thrown and not caught
 * by the execute method itself will be printed out with the stacktrace and the test
 * will be failed.
 *
 * <pre>
 * TestBody.test(new TestBody() {
 *     @Override public void execute() throws Exception {
 *          // assert stuff
 *     }
 * });
 * </pre>
 */
public abstract class TestBody {

    /**
     * Implement this method
     * @throws Exception
     */
    protected abstract void execute() throws Exception;

    protected void cleanUp() {
        /* override to do something else */
    }

    public static void test(TestBody testBody) {
        try {
            testBody.execute();
        } catch (Exception e) {
            FailUtil.sprintFail(e);
        } finally {
            testBody.cleanUp();
        }
    }
}
