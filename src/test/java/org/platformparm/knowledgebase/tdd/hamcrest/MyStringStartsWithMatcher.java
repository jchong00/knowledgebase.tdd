package org.platformparm.knowledgebase.tdd.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MyStringStartsWithMatcher extends TypeSafeMatcher<String> {

    private final String expectedPrefix;
    public MyStringStartsWithMatcher(String expectedPrefix) {
        this.expectedPrefix = expectedPrefix;
    }

    /**
     * Subclasses should implement this. The item will already have been checked for the
     * specific type and will never be null.
     */
    @Override
    protected boolean matchesSafely(String actual) {
        return actual.startsWith(this.expectedPrefix);
    }

    /**
     * Generates a description of the object.  The description may be part of a a description of
     * a larger object of which this is just a component, so it should be worded appropriately.
     *
     * @param description The description to be built or appended to.
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("검사하려는 문장은 다음 단어로 문자(열)로 시작되었습니다.")
            .appendText(this.expectedPrefix);
    }

    /**
     * Subclasses should override this. The item will already have been checked for the specific
     * type and will never be null.
     */
    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        String actualPrefix
            = actual.substring(0, Math.min(actual.length(), expectedPrefix.length()));
        mismatchDescription.appendText("이번 테스트 실행은 다음으로 시작 했습니다. : ").appendValue(actualPrefix);
    }

    static Matcher<String> myStringStartsWith(String prefix) {
        return new MyStringStartsWithMatcher(prefix);
    }
}
