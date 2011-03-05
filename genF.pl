#!perl -w

use warnings;
use strict;

my $n = 2;

while ($n < 27) {
    open J, ">frege/rt/Fun$n.java" or die "can't open $!";
    my @targs = map { "T$_" } (1..$n+1);
    my @xargs = map { "X$_" } (1..$n+1);
    my @nargs = map {"final Lazy<T$_> arg$_" } (1..$n);
    my $cnargs = join (",", @nargs);
    my @args  = map { "arg$_" } (1..$n);
    my $crargs = join(",", reverse @args);
    my $rt = $targs[$n];
    my $ctargs = join (",", @targs);
    my $cxargs = join (",", @xargs);
    my $cargs  = join (",", @args);
    # my $crargs = join (",", reverse @args);
    my $p = $n-1;
    my @ptargs = @targs; shift @ptargs;
    my $cptargs = join(",", @ptargs);
    my @pargs = map { "final Lazy<T$_> arg$_" } (2..$n);
    my @rpargs = reverse @pargs;
    my $crpargs = join(",", @rpargs);
    print J "package frege.rt;\r\n";
    print J <<'TEXT';
// $Author$
// $Date$
// $Rev$
// $Id$
TEXT
    print J <<"TEXT";
/**
 * <p> Frege functions with arity $n. </p>
 *
 * <p> See {\@link Fun1} for a general discussion of function values. </p>
 *
 */
public abstract class Fun$n<$ctargs> implements Value, Lazy<Fun$n<$ctargs>> {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {\@link Fun$p} that collects the
     * remaining arguments and, when evaluated, invokes the {\@link Fun$n#r} method of this
     * function.</p>
     *
     * \@return an instance of type <tt>Fun$p&lt;$cptargs&gt;</tt> that waits for the
     * remaining arguments to be supplied and calls back with all arguments.
     */
    final public Fun$p<$cptargs> a(final Lazy<T1> arg1) {
        return new Fun$p<$cptargs> () {
            final public Lazy<$rt> r($crpargs) {
                return Fun$n.this.r($crargs);
            }
        };
    }
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {\@link Unknown} that,
     * when evaluated, invokes the {\@link Fun$n#r} method of this
     * function.</p>
     *
     * Use of this method is preferrable if all arguments are known compared
     * to repeated invokation of the single argument form since intermediate
     * closure creation is saved.
     *
     * \@return an instance of type <tt>Unknown&lt;$rt&gt;</tt>
     */
    final public Unknown<$rt> a($cnargs) {
        return new Unknown<$rt> () {
            final public Lazy<$rt> _v() { return Fun$n.this.r($crargs); }
        };
    }
    /**
     * <p> Always <tt>0</tt> for function values. </p>
     * \@return 0
     */
    final public int     _c() { return 0; }          // interface Value
    /**
     * <p> Return this function object. </p>
     * \@return <tt>this</tt>
     */
    final public Fun$n<$ctargs> _e() { return this; }       // interface Lazy
    /**
     * <p> Return this function object. </p>
     * \@return <tt>this</tt>
     */
    final public Fun$n<$ctargs> _v() { return this; }       // interface Lazy
    /**
     * <p> Always <tt>false</tt> for function values. </p>
     * \@return <tt>false</tt>
     */
    final public boolean _u() { return false; }      // interface Lazy
    /**
     * <p> Run the function. </p>
     *
     * <p> The run method will be called by the {\@link Fun$p#r} method
     * of the function value resulting from <tt>this.a(...)</tt>.
     * It actually performs computation and
     * returns a result or another lazy value that will evaluate to the result.<br>
     * This method must be implemented by all subclasses.</p>
     *
     * <p>
     * Note that the arguments must be passed in reverse order. The reason is that
     * in this way the byte code for any intermediate closure will only have to
     * push its argument and invoke the next higher closure's <tt>r</tt> method.
     * A reordering of the arguments on the stack will not be needed. This could save
     * a substantial amounts of memory writes (I hope).
     * </p>
     *
     *
     * \@return boxed and possibly lazy result
     */
    abstract public Lazy<$rt> r($crpargs, Lazy<T1> arg1);
    /**
     * <p> Coerce the function to another type. </p>
     * <p> I see no other way to get around the limitations of the java type system, sorry.</p>
     * <p> This will be used in the case of constructor classes.</p>
     */
    \@SuppressWarnings("unchecked") 
    public final <$cxargs> Fun$n<$cxargs> coerce() { return (Fun$n<$cxargs>) this; }
     
}
TEXT
    # print "joined=", (join (",", @targs)), "\n";
    close J;
    $n++;
}