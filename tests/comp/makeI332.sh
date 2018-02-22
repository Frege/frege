#!/bin/sh
v="$1" # optional verbose flag
tmpd="`mktemp -d`"
trap 1 2 3 15
trap "rm -rf '$tmpd'" EXIT
javac -d "$tmpd" tests/comp/I332Java.java
java -cp build frege.compiler.Main $v -d "$tmpd" tests/comp/Issue332.fr
