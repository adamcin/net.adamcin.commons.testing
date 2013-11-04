/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

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
 * by the {@link #execute()} method itself will be printed out with the stack trace and the test
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

    /**
     * Called in the {@code finally} block. Default implementation does nothing.
     * Override to perform any necessary cleanup behavior for the {@link TestBody}
     * implementation
     */
    protected void cleanUp() {
        /* override to do something else */
    }

    /**
     * Static execution method.
     * @param testBody the TestBody to execute
     */
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
