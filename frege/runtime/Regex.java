/**
 * 
 */
package frege.runtime;

import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * This is the home of the {@link java.util.regex.Matcher} related functions 
 * referenced from {@link frege.Prelude}
 * 
 * <p> Most matcher operations implemented in
 * {@link java.util.regex.Matcher} change the state of the
 * matcher instance.
 * Therefore we can't use them directly in frege code. </p>
 *
 * <p> Instead, we provide here a re-implementation of the functionality
 * we need. The idea is to make a copy of the matcher <b>before</b> the
 * destructive operation, run the operation on the copy and return it
 * to frege code. This is safe because there is no reference to the
 * copy before the operation in frege code.</p>
 * <p>As a consequence, matcher operations like {@link Matcher#find()} or 
 * {@link Matcher#matches()} that return <b>boolean</b> in Java 
 * appear in Frege as function from Matcher to Matcher or Maybe Matcher,
 * because the results (i.e. {@link MatchResult#group()}) cannot be retrieved
 * from the quasi-immutable {@link java.util.regex.Matcher} that was passed in.
 * </p>
 *
 *
 * @author ingo
 *
 */
public final class Regex {

    /**
     * <p> Copy a {@link java.util.regex.Matcher} instance. </p>
     *
     *
     * <p>This function relies on the fact that
     * {@link java.util.regex.Matcher#toMatchResult} effectively makes a
     * copy (yet an incomplete one) of the current matcher. It completes the copy by invoking
     * {@link java.util.regex.Matcher#usePattern}
     * which sets up the internal arrays.</p>
     *
     * <p>The copy can not be used to retrieve information about the last match
     * operation of the original matcher.</p>
     *
     * @param  m the {@link Matcher} to copy
     * @return a copy of a matcher
     */

    public static Matcher clone(final Matcher m) {
        return ((Matcher)m.toMatchResult()).usePattern(m.pattern());
    }

    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @return the copy the operation was performed on
     */
    public static Matcher usePattern(final Matcher m,
                                                     final Pattern p) {
        return ((Matcher)m.toMatchResult()).usePattern(p);
    }
    
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#useAnchoringBounds
     * @return the copy the operation was performed on
     */
    public static java.util.regex.Matcher useAnchoringBounds(final java.util.regex.Matcher m,
                                                             final boolean b) {
        final java.util.regex.Matcher c = clone(m);
        c.useAnchoringBounds(b);
        return c;
    }
    
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#matches
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher matches(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.matches()) return c;
        return null;
    }
    
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher find(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.find()) return c;
        return null;
    }
    
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher usePatternAndFind(final java.util.regex.Matcher m,
                                                            final java.util.regex.Pattern p) {
        final java.util.regex.Matcher c = usePattern(m, p);
        final boolean emptymatch = m.start(0) == m.end(0);
        if (emptymatch)
            if (c.find(m.end(0))) return c; else return null;
        if (c.find()) return c;
        return null;
    }
    
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceFirst
     * @return a string
     */
    public static java.lang.String replaceFirst(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceFirst(r);
    }

    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceAll
     * @return a string
     */
    public static java.lang.String replaceAll(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceAll(r);
    }

}
