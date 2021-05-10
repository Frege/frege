#!perl -w

use warnings;
use strict;

my $n = 1;


while ($n < 27) {
    open J, ">frege/run/Func$n.java" or die "can't open $!";
    my @nargs = map {"final Object arg$_" } (1..$n);
    my $cnargs = join (", ", @nargs);
    my @args  = map { "arg$_" } (1..$n);
    my $crargs = join(",", reverse @args);
    my $rt = "Object";
    print J <<'LIZENZ';
/* 

    Copyright © 2015 - 2021, Ingo Wechsung
    All rights reserved.

    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:

        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.

        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution.
		
		Neither the name of the copyright holder
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

     */

LIZENZ

    print J "package frege.run;\n";
    print J <<"TEXT";
/**
 * <p> Frege functions with arity $n. </p>
 *
 * <p> See {\@link Function} for a general discussion of untyped function values. </p>
 *
 */
public interface Func$n<R> extends Function<R> {
    /**
     * <p> Apply the function uncurried. </p>
     *
     * \@return possibly lazy result
     */
    public R apply($cnargs);
}
TEXT
    close J;
    $n++;
}
