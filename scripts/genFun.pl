#!perl -w

use warnings;
use strict;

my $n = 2;

sub mkFun {
    my @args = @_;
    if (scalar @args > 1) {
        my $t1 = shift @args;
        my $t2 = mkFun(@args);
        return "Fun<$t1, $t2>";
    }
    else {
        return shift @args;
    }
}

while ($n < 27) {
    open J, ">frege/runtime/Fun$n.java" or die "can't open $!";
    my @targs = map { "T$_" } (1..$n+1);
    my $ext = mkFun(@targs);
    my @xargs = map { "X$_" } (1..$n+1);
    my @nargs = map {"final Object arg$_" } (1..$n);
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
    my @pargs = map { "final Object arg$_" } (2..$n);
    my @rpargs = reverse @pargs;
    my $crpargs = join(",", @rpargs);
    print J <<'LIZENZ';
/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011 - 2021, Ingo Wechsung
    All rights reserved.

    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:

        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.

        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. Neither the name of the copyright holder
        nor the names of its contributors may be used to endorse or
        promote products derived from this software without specific
        prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE
    COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
    PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
    OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
    LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
    USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
    AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
    LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
    IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
    THE POSSIBILITY OF SUCH DAMAGE.

    «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•» */

LIZENZ

    print J "package frege.runtime;\r\n";
    print J <<"TEXT";
/**
 * <p> Frege functions with arity $n. </p>
 *
 * <p> See {\@link Fun1} for a general discussion of untyped function values. </p>
 *
 */
public abstract class Fun$n<X> extends Lambda {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {\@link Fun$p} that collects the
     * remaining arguments and, when evaluated, invokes the {\@link Fun$n#eval} method of this
     * class.</p>
     *
     * \@return an instance of type <tt>Fun$p</tt> that waits for the
     * remaining arguments to be supplied and calls back with all arguments.
     */
    final public Fun$p<X> apply(final Object arg1) {
        return new Fun$p<X> () {
            final public X eval($crpargs) {
                return Fun$n.this.eval($crargs);
            }
        };
    }
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {\@link Delayed} that,
     * when evaluated, invokes the {\@link Fun$n#eval} method of this
     * function.</p>
     *
     * Use of this method is preferable compared
     * to repeated invokation of the single argument form since intermediate
     * closure creation is saved.
     *
     * \@return an instance of type {\@link Delayed} 
     */
    final public Delayed apply($cnargs) {
        return new Delayed () {
            final public X eval() { return Fun$n.this.eval($crargs); }
        };
    }
    
	/**
     * <p>Force the Java typechecker to accept sensible substitutions.</p>
     * <p>The Java typechecker won't accept a function that promises 
     * to return X in place of a function that promises to return {\@link Object},
     * though this should be completely save, shouldn't it?</p>
     * <p>It also refuses to accept any frege type, when {\@link Lazy} is demanded,
     * though every frege type implements {\@link Lazy}.</p>   
     * 
     * \@return This function, now promising to return a broader type
     */
	\@SuppressWarnings("unchecked")
	final public  <B, Y extends B> Fun$n<B> toSuper(Fun$n<Y> it) {
    	return (Fun$n<B>) it;    	
    }

    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {\@link Fun$p#eval} method
     * of the object resulting from <tt>this.apply(...)</tt>.
     * It actually performs computation and
     * returns a result.</p>
	 * 
     * <p>Functions always return the announced type (which can be a lazy).</p>
	 *
     * <p>This method must be implemented by all subclasses.</p>
     *
     * <p>
     * Note that the arguments must be passed in reverse order. The reason is that
     * in this way the byte code for any intermediate closure will only have to
     * push its argument and invoke the next higher closure's <tt>eval</tt> method.
     * A reordering of the arguments on the stack will not be needed. This could save
     * a substantial amounts of memory writes (I hope).
     * </p>
     *
     *
     * \@return result
     */
    abstract public X eval($crpargs, final Object arg1);
}
TEXT
    # print "joined=", (join (",", @targs)), "\n";
    close J;
    $n++;
}
