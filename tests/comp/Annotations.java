package tests.comp;

import frege.runtime.Meta;

/*

	ingo@freguntu:~/dev/test$ j9 -version
	openjdk version "1.8.0_162"
	OpenJDK Runtime Environment (build 1.8.0_162-b12)
	Eclipse OpenJ9 VM (build openj9-0.8.0, JRE 1.8.0 Linux amd64-64 Compressed References 20180315_120 (JIT enabled, AOT enabled)
	OpenJ9   - e24e8aa9
	OMR      - 3e8296b4
	JCL      - ee1e77df1d based on jdk8u162-b12)
	ingo@freguntu:~/dev/test$ javac -version
	javac 9-ea
	ingo@freguntu:~/dev/test$ javac -d build -source 1.8 -target 1.8 tests/comp/Annotations.java 
	warning: [options] bootstrap class path not set in conjunction with -source 1.8
	1 warning
	ingo@freguntu:~/dev/test$ java -version
	java version "9-ea"
	Java(TM) SE Runtime Environment (build 9-ea+99-jigsaw-nightly-h4279-20160114)
	Java HotSpot(TM) 64-Bit Server VM (build 9-ea+99-jigsaw-nightly-h4279-20160114, mixed mode)
	ingo@freguntu:~/dev/test$ java -cp build tests.comp.Annotations
	annotation present: true
	ingo@freguntu:~/dev/test$ j9 -cp build tests.comp.Annotations
	Exception in thread "main" java.lang.annotation.AnnotationFormatError: java.lang.IllegalArgumentException: Constant pool index out of bounds
		at sun.reflect.annotation.AnnotationParser.parseAnnotations(AnnotationParser.java:77)
		at java.lang.Class.getAnnotationCache(Class.java:2447)
		at java.lang.Class.getAnnotation(Class.java:2095)
		at tests.comp.Annotations.main(Annotations.java:7295)
	Caused by: java.lang.IllegalArgumentException: Constant pool index out of bounds
		at sun.reflect.ConstantPool.getClassAt0(Native Method)
		at sun.reflect.ConstantPool.getClassAt(ConstantPool.java:37)
		at sun.reflect.annotation.AnnotationParser.parseAnnotation2(AnnotationParser.java:244)
		at sun.reflect.annotation.AnnotationParser.parseAnnotations2(AnnotationParser.java:120)
		at sun.reflect.annotation.AnnotationParser.parseAnnotations(AnnotationParser.java:72)
		... 3 more

 */

@SuppressWarnings("unused")
@Meta.FregePackage(
  source="xx", time=1522452236973L, jmajor=1, jminor=8,
  doc="",
  imps={}, nmss={},
  symas={
    @Meta.SymA(
      offset=26195, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="String"), vars={},
      typ=0, kind=3,
      doc="" +"\n"+ "    'String' values are based on Java\\'s @java.lang.String@ objects." +"\n"+
      "    'String' is an alias for 'StringJ' 'Char'" +"\n"+ "         "
    ),
    @Meta.SymA(
      offset=85932, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="IO"), vars={},
      typ=1, kind=3, doc=" @IO a@ is an abbreviation for 'ST' 'RealWorld' @a@   "
    ),
    @Meta.SymA(
      offset=39582, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="CatchAll"), vars={},
      typ=2, kind=3,
      doc=" warning: probably catches more exceptions than you want to handle, use (Exception1|Exception2|Result)"
      +"\n"+ "    This is the principal return type for java methods that are expected to" +"\n"+
      "    throw exceptions." +"\n"+ "    " +"\n"+
      "    It is strongly recommended to use more specific exception types." +"\n"+ "      "
    )
  },
  symcs={
    @Meta.SymC(
      offset=60323, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), tau=10,
      sups={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num")
      },
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Double")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=60532,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="positiveInfinity"), stri="s",
          sig=3, abst=true, depth=0, rkind=33, publik=false,
          doc=" positive infinity, negative infinity and not a number   "
        ),
        @Meta.SymV(
          offset=60568, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="nan"), stri="s",
          sig=3, abst=true, depth=0, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=60550,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="negativeInfinity"), stri="s",
          sig=3, abst=true, depth=0, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=60371, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="/"),
          stri="s(uu)", sig=5, abst=true, depth=2, rkind=33, publik=false, doc=" the division operator   ", op=69
        ),
        @Meta.SymV(
          offset=60440, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="fromDouble"),
          stri="s(u)", sig=7, abst=true, depth=1, rkind=33, publik=false,
          doc=" convert a 'Double' to any 'Real' value   "
        )
      },
      doc="" +"\n"+ " * The 'Real' class provides the division operator ('/')." +"\n"+
      "      "
    ),
    @Meta.SymC(
      offset=57796, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12,
      sups={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord")
      },
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num_Long")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=58443, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="zero"), stri="s",
          sig=8, abst=true, depth=0, rkind=33, publik=false, doc=" the number 0 in the instantiated type   "
        ),
        @Meta.SymV(
          offset=58008, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="subtract"),
          stri="s(uu)", sig=10, depth=2, rkind=49, expr=7,
          doc=" use @(subtract a)@ instead of @\\\\b -> b-a@ in sections   "
        ),
        @Meta.SymV(
          offset=58383, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="one"), stri="s",
          sig=8, abst=true, depth=0, rkind=33, publik=false, doc=" the number 1 in the instantiated type   "
        ),
        @Meta.SymV(
          offset=58316, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="sign"),
          stri="s(u)", sig=11, depth=1, rkind=49, expr=19, doc=" @sign n@ is -1 if n<0, 1 if n>0 and 0 otherwise   "
        ),
        @Meta.SymV(
          offset=60171, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isNumber"),
          stri="s(u)", sig=12, depth=1, rkind=49, expr=31,
          doc=" Returns @true@ if @n@ is neither infinite (see 'isInfinite') nor NaN (see 'isNaN')." +"\n"+
          "" +"\n"+ "        Note that certain properties for functions on numbers are true only under the assumption"
          +"\n"+ "        that the argument values are numbers." +"\n"+ "" +"\n"+
          "        The default implementation is" +"\n"+ "        > isNumber n = !(isInfinite n) && !(isNaN n)" +"\n"+
          "        so that the function should always compute" +"\n"+
          "        the right answer as long as 'isInfinite' and 'isNaN' do." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=59677, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isNaN"),
          stri="s(u)", sig=12, depth=1, rkind=49, expr=34,
          doc=" Floating point number types may have a special values for" +"\n"+
          "        _not a number_ (NaN). For example, @0d / 0d@ is NaN." +"\n"+
          "        @isNaN n@ yields @true@ if @n@ is the special value that indicates that" +"\n"+
          "        @n@ is not a number and @false@ in all other cases." +"\n"+ "" +"\n"+
          "        The default implementation always returns @false@ so that implementors of" +"\n"+
          "        instances for types without such a special values need not care." +"\n"+
          "" +"\n"+ "        See also 'isNumber'." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=58239, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="negate"),
          stri="s(u)", sig=13, depth=1, rkind=49, expr=38,
          doc=" Negates a number n such that if n is a number   " +"\n"+ "" +"\n"+ " > n + negate n == 0   "
        ),
        @Meta.SymV(
          offset=58730, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInteger"),
          stri="s(u)", sig=15, abst=true, depth=1, rkind=33, publik=false,
          doc=" converts an 'Integer' value to the instantiated type   "
        ),
        @Meta.SymV(
          offset=58139, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="abs"),
          stri="s(u)", sig=13, depth=1, rkind=49, expr=45, doc=" Computes the absolute value   "
        ),
        @Meta.SymV(
          offset=58645, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInt"),
          stri="s(u)", sig=17, abst=true, depth=1, rkind=33, publik=false,
          doc=" converts an 'Int' value to the instantiated type   "
        ),
        @Meta.SymV(
          offset=58081, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="*"),
          stri="s(uu)", sig=10, abst=true, depth=2, rkind=33, publik=false,
          doc=" Computes the product of two numbers   ", op=69
        ),
        @Meta.SymV(
          offset=57853, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="+"),
          stri="s(uu)", sig=10, abst=true, depth=2, rkind=33, publik=false, doc=" Computes the sum of two numbers   ",
          op=68
        ),
        @Meta.SymV(
          offset=57923, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="-"),
          stri="s(uu)", sig=10, abst=true, depth=2, rkind=33, publik=false,
          doc=" Computes the difference of two numbers   ", op=68
        ),
        @Meta.SymV(
          offset=59164, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isInfinite"),
          stri="s(u)", sig=12, depth=1, rkind=49, expr=47,
          doc=" Floating point number types may have special values for _infinity_" +"\n"+
          "        and _negative infinity_. @isFinite n@ yields @true@ if @n@ is an infinite" +"\n"+
          "        value and @false@ in all other cases." +"\n"+ "" +"\n"+
          "        The default implementation always returns @false@ so that implementors of" +"\n"+
          "        instances for types without special values for infinity need not care." +"\n"+
          "" +"\n"+ "        See also 'isNumber'." +"\n"+ "         "
        )
      },
      doc=" The 'Num' class provides the operators ('+'), ('-') and ('*') as well as" +"\n"+
      "    some functions that are common for all numeric types." +"\n"+ "         "
    ),
    @Meta.SymC(
      offset=52817, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), tau=16,
      sups={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq")},
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="StringJ"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_String"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Long")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=54098, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="min"),
          stri="s(uu)", sig=19, depth=2, rkind=49, expr=54, doc=" > min a b = if a < b then a else b   "
        ),
        @Meta.SymV(
          offset=53234, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="compare"),
          stri="s(uu)", sig=20, depth=2, rkind=49, expr=60, op=97
        ),
        @Meta.SymV(
          offset=54030, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="max"),
          stri="s(uu)", sig=19, depth=2, rkind=49, expr=68, doc=" > max a b = if a > b then a else b   "
        ),
        @Meta.SymV(
          offset=53763, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">"),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=78,
          doc=" Relational @>@ operator. Obeys the following laws:" +"\n"+ "        > if a > b && b > c then a > c"
          +"\n"+ "        > a > b == b < a" +"\n"+ "             ",
          op=98
        ),
        @Meta.SymV(
          offset=53179, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<=>"),
          stri="s(uu)", sig=20, abst=true, depth=2, rkind=33, publik=false,
          doc=" This operator must be defined in all instances. It compares its operands and" +"\n"+
          "        returns 'Lt' if the first is lower than the second, 'Gt' if the first is" +"\n"+
          "        greater than the second and 'Ordering.Eq' otherwise." +"\n"+ ""
          +"\n"+ "        The following shall be invariantly true:" +"\n"+
          "        > case a <=> b of { Eq -> a == b; _ -> a != b }" +"\n"+ "" +"\n"+ "             ",
          op=97
        ),
        @Meta.SymV(
          offset=54704, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="=="),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=87,
          doc=" This implementation for the ('==') operator is being used in instances" +"\n"+
          "        of 'Ord' when the instantiated type is not already an instance of 'Eq'." +"\n"+
          "         ",
          op=96
        ),
        @Meta.SymV(
          offset=54934, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="!="),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=95,
          doc=" This implementation for the ('!=') operator is being used in instances" +"\n"+
          "        of 'Ord' when the instantiated type is not already an instance of 'Eq'." +"\n"+
          "         ",
          op=96
        ),
        @Meta.SymV(
          offset=53404, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<"),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=104,
          doc=" Relational @<@ operator. Obeys the following laws:" +"\n"+ "        > if a < b && b < c then a < c"
          +"\n"+ "        > a < b == b > a" +"\n"+ "             ",
          op=98
        ),
        @Meta.SymV(
          offset=53601, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<="),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=112,
          doc=" Relational @<=@ operator. Obeys the following laws:" +"\n"+ "        > if a <= b && b <= c then a <= c"
          +"\n"+ "        > a <= b == b >= a" +"\n"+ "        > a <= b == !(a > b)" +"\n"+
          "             ",
          op=98
        ),
        @Meta.SymV(
          offset=53960, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">="),
          stri="s(uu)", sig=21, depth=2, rkind=49, expr=120,
          doc=" Relational @>=@ operator. Obeys the following laws:" +"\n"+ "        > if a >= b && b >= c then a >= c"
          +"\n"+ "        > a >= b == b <= a" +"\n"+ "        > a >= b == !(a < b)" +"\n"+
          "             ",
          op=98
        )
      },
      doc="" +"\n"+ "  The 'Ord' class provides relational operators as well as the functions 'max' and 'min'." +"\n"+
      "  The default implementation defines them all in terms of the _compare_ operator '<=>'." +"\n"+
      "" +"\n"+ "  Making some type an instance of 'Ord' makes it automatically an instance of 'Eq' if it" +"\n"+
      "  is not one already and if it has an implementation for 'hashCode'. " +"\n"+
      "  The operators '==' and '!=' will be defined in terms of '<=>'." +"\n"+ ""
      +"\n"+ "  Instances of 'Ord' can be derived automatically for algebraic data types when" +"\n"+
      "  all elements of the type are themselves instances of 'Ord' and when the type is" +"\n"+
      "  an instance of 'Eq'." +"\n"+ "      "
    ),
    @Meta.SymC(
      offset=62519, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18,
      sups={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num")
      },
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Long")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=62970, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="rem"),
          stri="s(uu)", sig=23, abst=true, depth=2, rkind=33, publik=false,
          doc=" The remainder á la Java operator @%@ - @a `rem` b@ has same sign as @a@   " +"\n"+
          "" +"\n"+ " > forAll arbitrary (\\a -> forAll arbitrary (\\b -> (a `quot` b) * b + (a `rem` b) = a   " +"\n"+
          "" +"\n"+ " This behaviour is the same as in Haskell   ",
          op=69
        ),
        @Meta.SymV(
          offset=62570, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="quot"),
          stri="s(uu)", sig=23, abst=true, depth=2, rkind=33, publik=false, op=69
        ),
        @Meta.SymV(
          offset=62624, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="quotRem"),
          stri="s(uu)", sig=24, depth=2, rkind=49, expr=133, doc=" Haskell compatibility   "
        ),
        @Meta.SymV(
          offset=65261, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="odd"),
          stri="s(u)", sig=25, abst=true, depth=1, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=65323, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powf"),
          stri="s(uu)", sig=27, depth=2, rkind=185, expr=170, doc=" helper for 'Integral.^'   "
        ),
        @Meta.SymV(
          offset=65548, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powg"),
          stri="s(uuu)", sig=28, depth=3, rkind=185, expr=201, doc=" helper for 'Integral.^'   "
        ),
        @Meta.SymV(
          offset=64854, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="lcm"),
          stri="s(uu)", sig=23, depth=2, rkind=49, expr=224,
          doc=" 'lcm' @x y@ is the smallest positive integer that both @x@ and @y@ divide.   "
        ),
        @Meta.SymV(
          offset=64683, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcd"),
          stri="s(uu)", sig=23, depth=2, rkind=49, expr=233,
          doc="" +"\n"+ "        'gcd' @x y@ is the non-negative factor of both @x@ and @y@ of which" +"\n"+
          "        every common factor of @x@ and @y@ is also a factor; for example" +"\n"+
          "        'gcd' @4 2 = 2@, @'gcd' (-4) 6 = 2@, @'gcd' 0 4@ = @4@. @'gcd' 0 0@ = @0@." +"\n"+
          "        (That is, the common divisor that is \\\"greatest\\\" in the divisibility" +"\n"+
          "        preordering.)" +"\n"+ "    " +"\n"+
          "        Note: Since for signed fixed-width integer types, 'abs' 'minBound' < @0@," +"\n"+
          "        the result may be negative if one of the arguments is 'minBound' (and" +"\n"+
          "        necessarily is if the other is @0@ or 'minBound') for such types." +"\n"+
          "             "
        ),
        @Meta.SymV(
          offset=64688, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcdHelper"),
          stri="s(uu)", sig=23, depth=2, rkind=185, expr=248,
          doc=" worker function for 'Integral.gcd', needs not pollute the namespace   "
        ),
        @Meta.SymV(
          offset=65255, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="even"),
          stri="s(u)", sig=25, abst=true, depth=1, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=62633, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="divMod"),
          stri="s(uu)", sig=24, depth=2, rkind=49, expr=260
        ),
        @Meta.SymV(
          offset=63870, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="big"),
          stri="s(u)", sig=29, abst=true, depth=1, rkind=33, publik=false,
          doc=" every integral number can be converted to a big 'Integer'   "
        ),
        @Meta.SymV(
          offset=65099, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="^"),
          stri="s(uu)", sig=27, depth=2, rkind=49, expr=282,
          doc=" @ x ^ n @  raise _x_ to the _n_-th power   " +"\n"+ "" +"\n"+ " The exponent must be positive.    ",
          op=87
        ),
        @Meta.SymV(
          offset=62565, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="div"),
          stri="s(uu)", sig=23, depth=2, rkind=49, expr=310, doc=" integer division   ", op=69
        ),
        @Meta.SymV(
          offset=63950,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="fromIntegral"),
          stri="s(u)", sig=30, depth=1, rkind=49, expr=316, doc=" every 'Num' can be made from an 'Integral'   "
        ),
        @Meta.SymV(
          offset=62993, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="mod"),
          stri="s(uu)", sig=23, depth=2, rkind=49, expr=340,
          doc=" This modulo operator works so that   " +"\n"+ "" +"\n"+
          " > forAll arbitrary (\\a -> forAll arbitrary (\\b -> (a `div` b) * b + (a `mod` b) = a))   " +"\n"+
          "" +"\n"+ " In addition, it is the case that   " +"\n"+ "" +"\n"+
          " > forAll arbitrary (\\a -> forAll arbitrary (\\b -> @(a `quot` b) == (a `div` b) || (a `quot` b) == (a `div` b)-1))   ",
          op=69
        )
      },
      doc=" Class 'Integral' provides division and remainder operations for integral numbers.   "
    ),
    @Meta.SymC(
      offset=55557, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24,
      sups={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord")
      },
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Long")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=56497, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="succ"),
          stri="s(u)", sig=32, abst=true, depth=1, rkind=33, publik=false,
          doc=" @succ e@ is the successor of @e@ or 'undefined' if there is no such successor.   "
        ),
        @Meta.SymV(
          offset=55909, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="ord"),
          stri="s(u)", sig=33, abst=true, depth=1, rkind=33, publik=false,
          doc=" @ord e@ returns the ordinal number associated with the value @e@. For" +"\n"+
          "     * enumeration types, 'ord' is the same as 'constructor', for 'Int', it is the" +"\n"+
          "     * identity function." +"\n"+
          "     * Some types, like 'Long', cannot map all their values to 'Int', in such cases" +"\n"+
          "     * the result of applying 'ord' may be meaningless." +"\n"+ "          "
        ),
        @Meta.SymV(
          offset=56602, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="pred"),
          stri="s(u)", sig=32, abst=true, depth=1, rkind=33, publik=false,
          doc=" @pred e@ is the predecessor of @e@ or 'undefined' if there is no predecessor.   "
        ),
        @Meta.SymV(
          offset=56389, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="from"),
          stri="s(u)", sig=34, abst=true, depth=1, rkind=33, publik=false,
          doc="" +"\n"+ "     * @T.from i@ maps the 'Int' value @i@ to a value of @T@, such that" +"\n"+
          "     >   ord (T.from i) == i" +"\n"+
          "     * unless there is no value @e@ of @T@ so that @ord e == i@. In the" +"\n"+
          "     * latter case, the result is 'undefined'." +"\n"+ "          "
        ),
        @Meta.SymV(
          offset=57030, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="hashCode"),
          stri="s(u)", sig=33, depth=1, rkind=49, expr=344,
          doc=" default implementation for 'Eq.hashCode' is same as 'ord'   "
        ),
        @Meta.SymV(
          offset=57167, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromThen"),
          stri="s(uu)", sig=35, abst=true, depth=2, rkind=33, publik=false, doc=" used in translating @[n,n'..]   "
        ),
        @Meta.SymV(
          offset=57088,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromThenTo"),
          stri="s(uuu)", sig=36, abst=true, depth=3, rkind=33, publik=false, doc=" used in translating @[n,n'..m]   "
        ),
        @Meta.SymV(
          offset=56120, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="<=>"),
          stri="s(uu)", sig=37, depth=2, rkind=49, expr=353,
          doc="" +"\n"+ "     * This is the default implementation of the compare operator," +"\n"+
          "     * that makes each 'Enum' type an 'Ord' type automatically." +"\n"+
          "     * > a <=> b  =  (ord a).<=>  (ord b)" +"\n"+ "          ",
          op=97
        ),
        @Meta.SymV(
          offset=57420, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFrom"),
          stri="s(u)", sig=38, depth=1, rkind=49, expr=360, doc=" used in translating @[n..]   "
        ),
        @Meta.SymV(
          offset=57237, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromTo"),
          stri="s(uu)", sig=35, depth=2, rkind=177, expr=382, doc=" used in translating @[n..m]   "
        )
      },
      doc="" +"\n"+ " * Class 'Enum' defines operations on sequentially ordered types." +"\n"+
      " *" +"\n"+ " * A type that is an instance of 'Enum' is also an instance" +"\n"+
      " * of 'Ord' (and, in turn, of 'Eq')." +"\n"+ " *" +"\n"+
      " * Instances of 'Enum' may be derived for any enumeration type" +"\n"+
      " * (types whose constructors have no fields)." +"\n"+
      " * If there is no 'hashCode' provided, it will be the same as 'ord'." +"\n"+ " *"
      +"\n"+ " * Enumeration types have special syntactic support with the range list:" +"\n"+
      " > [a .. b]" +"\n"+ " > [a ..]" +"\n"+ " > [a,b .. c]" +"\n"+ " > [a,b ..]" +"\n"+
      "      "
    ),
    @Meta.SymC(
      offset=70208, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), tau=23,
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Long")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=70294, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded", member="maxBound"),
          stri="s", sig=39, abst=true, depth=0, rkind=33, publik=false, doc=" the upper bound   "
        ),
        @Meta.SymV(
          offset=70252, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded", member="minBound"),
          stri="s", sig=39, abst=true, depth=0, rkind=33, publik=false, doc=" the lower bound   "
        )
      },
      doc="" +"\n"+ " * A class for data types that have a lower and an upper bound." +"\n"+
      " *" +"\n"+ " * Instances of 'Bounded' can be derived automatically for enumeration types." +"\n"+
      "      "
    ),
    @Meta.SymC(
      offset=4422, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=24,
      ins1={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="()"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="[]"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="StringJ")
      },
      ins2={
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Integer"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Long"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Char"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Bool"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_()"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Float"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Double"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Int"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_[]"),
        @Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_String")
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=5843, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="hashCode"),
          stri="s(u)", sig=40, abst=true, depth=1, rkind=33, publik=false,
          doc=" Compute a hash code.    " +"\n"+ "" +"\n"+ " The following rules shall hold in all instances:   " +"\n"+
          "" +"\n"+ " > a == b ==> hashCode a == hashCode b   " +"\n"+ "" +"\n"+
          " > hashCode a != hashCode b ==> a != b   " +"\n"+ "" +"\n"+
          " In addition, unequal values should produce unequal hash codes more likely than not.   " +"\n"+
          "" +"\n"+ "" +"\n"+
          "        The hash code in derived instances for algebraic data types is computed as follows:" +"\n"+
          "        > hashCode v = case v of" +"\n"+
          "        >    Con_1 f_1 ... f_k -> fold mkhash 1 [1, hashCode f_1, ..., hashCode f_k ]" +"\n"+
          "        >    ..." +"\n"+
          "        >    Con_n f_1 ... f_k -> fold mkhash 1 [n, hashCode f_1, ..., hashCode f_k ]" +"\n"+
          "        >   where mkhash a b = (31 * a) + b" +"\n"+
          "        Here, the @Con_i@ with @i>0@ are the constructors, and in each clause" +"\n"+
          "        the @f_j@ with @j >= 0@ are bound to the fields of the constructor under" +"\n"+
          "        consideration." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=4903, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="!="), stri="s(uu)",
          sig=41, depth=2, rkind=49, expr=389,
          doc=" Check for inequality. The default implementation obeys the laws   " +"\n"+
          "" +"\n"+ " > !(a != a)   " +"\n"+ "" +"\n"+ " > (a != b) == !(a == b)   " +"\n"+
          "" +"\n"+ " > (a == b) != (a != b)   " +"\n"+ "" +"\n"+
          " These laws shall also be obeyed in all implementations.   ",
          op=96
        ),
        @Meta.SymV(
          offset=4611, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="=="), stri="s(uu)",
          sig=41, abst=true, depth=2, rkind=33, publik=false,
          doc=" Check for equality. This function is required in all instances.   " +"\n"+
          "" +"\n"+ " The basic law of philosophy   " +"\n"+ "" +"\n"+ " > a == a   " +"\n"+
          "" +"\n"+ " shall be obeyed by all implementations.   ",
          op=96
        )
      },
      doc=" " +"\n"+ "  The type class 'Eq' provides operators '==', '!=' and 'hashCode'." +"\n"+
      "  " +"\n"+ "  All types whose values can be compared for equality should be instances of" +"\n"+
      "  this class. For algebraic data types instances can be automatically derived" +"\n"+
      "  if all components are themselves instances of 'Eq'." +"\n"+ "       "
    )
  },
  symis={
    @Meta.SymI(
      offset=78436, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Double"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), typ=6, lnks={},
      funs={
        @Meta.SymV(
          offset=78659, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="zero"),
          stri="s", sig=6, depth=0, rkind=49
        ),
        @Meta.SymV(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="sign"),
          stri="s(s)", sig=42, depth=1, rkind=49, doc="inherited from 'Num.sign'"
        ),
        @Meta.SymV(
          offset=38297,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="positiveInfinity"),
          stri="s", sig=6, nativ="java.lang.Double.POSITIVE_INFINITY", pur=true, depth=0,
          rkind=33, doc=" positive infinity   "
        ),
        @Meta.SymV(
          offset=78436,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="subtract"),
          stri="s(ss)", sig=43, depth=2, rkind=49, doc="inherited from 'Num.subtract'"
        ),
        @Meta.SymV(
          offset=38405,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="negativeInfinity"),
          stri="s", sig=6, nativ="java.lang.Double.NEGATIVE_INFINITY", pur=true, depth=0,
          rkind=33, doc=" negative infinity   "
        ),
        @Meta.SymV(
          offset=78700, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="negate"),
          stri="s(s)", sig=44, nativ="-", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=78436,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isNumber"),
          stri="s(s)", sig=45, depth=1, rkind=49, doc="inherited from 'Num.isNumber'"
        ),
        @Meta.SymV(
          offset=78825,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isInfinite"),
          stri="s(s)", sig=45, nativ="java.lang.Double.isInfinite", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=78898, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isNaN"),
          stri="s(s)", sig=45, nativ="java.lang.Double.isNaN", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=38538, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="nan"),
          stri="s", sig=6, nativ="java.lang.Double.NaN", pur=true, depth=0, rkind=33,
          doc=" the NaN value provided by the Java runtime   "
        ),
        @Meta.SymV(
          offset=78674, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="one"),
          stri="s", sig=6, depth=0, rkind=49
        ),
        @Meta.SymV(
          offset=78735,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromInt"), stri="s(s)",
          sig=46, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="abs"),
          stri="s(s)", sig=44, depth=1, rkind=49, doc="inherited from 'Num.abs'"
        ),
        @Meta.SymV(
          offset=78792,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromDouble"),
          stri="s(s)", sig=44, depth=1, rkind=49, expr=391
        ),
        @Meta.SymV(
          offset=78527, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="-"),
          stri="s(ss)", sig=43, nativ="-", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=78575, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="*"),
          stri="s(ss)", sig=43, nativ="*", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=78479, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="+"),
          stri="s(ss)", sig=43, nativ="+", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=78623, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="/"),
          stri="s(ss)", sig=43, nativ="/", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=78763,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromInteger"),
          stri="s(s)", sig=47, depth=1, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=77414, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real_Float"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), typ=48, lnks={},
      funs={
        @Meta.SymV(
          offset=77624, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="zero"),
          stri="s", sig=48, depth=0, rkind=49
        ),
        @Meta.SymV(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="sign"),
          stri="s(s)", sig=49, depth=1, rkind=49, doc="inherited from 'Num.sign'"
        ),
        @Meta.SymV(
          offset=36349,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="positiveInfinity"),
          stri="s", sig=48, nativ="java.lang.Float.POSITIVE_INFINITY", pur=true, depth=0,
          rkind=33, doc=" positive infinity   "
        ),
        @Meta.SymV(
          offset=77414,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="subtract"),
          stri="s(ss)", sig=50, depth=2, rkind=49, doc="inherited from 'Num.subtract'"
        ),
        @Meta.SymV(
          offset=36455,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="negativeInfinity"),
          stri="s", sig=48, nativ="java.lang.Float.NEGATIVE_INFINITY", pur=true, depth=0,
          rkind=33, doc=" negative infinity   "
        ),
        @Meta.SymV(
          offset=77668, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="negate"),
          stri="s(s)", sig=51, nativ="-", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=77414,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isNumber"), stri="s(s)",
          sig=52, depth=1, rkind=49, doc="inherited from 'Num.isNumber'"
        ),
        @Meta.SymV(
          offset=77802,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isInfinite"),
          stri="s(s)", sig=52, nativ="java.lang.Float.isInfinite", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=77873, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isNaN"),
          stri="s(s)", sig=52, nativ="java.lang.Float.isNaN", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=36586, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="nan"),
          stri="s", sig=48, nativ="java.lang.Float.NaN", pur=true, depth=0, rkind=33,
          doc=" the NaN value provided by the Java runtime   "
        ),
        @Meta.SymV(
          offset=77640, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="one"),
          stri="s", sig=48, depth=0, rkind=49
        ),
        @Meta.SymV(
          offset=77701, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromInt"),
          stri="s(s)", sig=53, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="abs"),
          stri="s(s)", sig=51, depth=1, rkind=49, doc="inherited from 'Num.abs'"
        ),
        @Meta.SymV(
          offset=77763,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromDouble"),
          stri="s(s)", sig=54, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=77501, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="-"),
          stri="s(ss)", sig=50, nativ="-", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=77546, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="*"),
          stri="s(ss)", sig=50, nativ="*", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=77456, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="+"),
          stri="s(ss)", sig=50, nativ="+", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=77591, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="/"),
          stri="s(ss)", sig=50, nativ="/", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=77728,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromInteger"),
          stri="s(s)", sig=55, depth=1, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=68241, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=56, lnks={},
      funs={
        @Meta.SymV(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="max"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="min"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=68466, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member=">="),
          stri="s(ss)", sig=58, nativ=">=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @>=@ operator for comparison of 'Long' values.   ", op=98
        ),
        @Meta.SymV(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="compare"),
          stri="s(uu)", sig=59, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=68732, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<=>"),
          stri="s(ss)", sig=59, depth=2, rkind=49, op=97
        ),
        @Meta.SymV(
          offset=68581, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<"),
          stri="s(ss)", sig=58, nativ="<", pur=true, depth=2, rkind=33,
          doc=" Uses the java @<@ operator for comparison of 'Long' values.   ", op=98
        ),
        @Meta.SymV(
          offset=68350, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<="),
          stri="s(ss)", sig=58, nativ="<=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @<=@ operator for comparison of 'Long' values.   ", op=98
        ),
        @Meta.SymV(
          offset=68696, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member=">"),
          stri="s(ss)", sig=58, nativ=">", pur=true, depth=2, rkind=33,
          doc=" Uses the java @>@ operator for comparison of 'Long' values.   ", op=98
        )
      }
    ),
    @Meta.SymI(
      offset=74887, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_String"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=0, lnks={},
      funs={
        @Meta.SymV(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="min"),
          stri="s(ss)", sig=60, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="compare"),
          stri="s(uu)", sig=61, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="max"),
          stri="s(ss)", sig=60, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=74960, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member=">"),
          stri="s(ss)", sig=62, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=75036, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member=">="),
          stri="s(ss)", sig=62, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=75074, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<="),
          stri="s(ss)", sig=62, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=74998, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<"),
          stri="s(ss)", sig=62, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=74918, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<=>"),
          stri="s(ss)", sig=61, depth=2, rkind=49, op=97
        )
      }
    ),
    @Meta.SymI(
      offset=66242, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=16, lnks={},
      funs={
        @Meta.SymV(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="max"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="min"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=66462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member=">="),
          stri="s(ss)", sig=64, nativ=">=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @>=@ operator for comparison of 'Int' values.   ", op=98
        ),
        @Meta.SymV(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="compare"),
          stri="s(uu)", sig=65, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=66720, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<=>"),
          stri="s(ss)", sig=65, depth=2, rkind=49, op=97
        ),
        @Meta.SymV(
          offset=66574, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<"),
          stri="s(ss)", sig=64, nativ="<", pur=true, depth=2, rkind=33,
          doc=" Uses the java @<@ operator for comparison of 'Int' values.   ", op=98
        ),
        @Meta.SymV(
          offset=66349, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<="),
          stri="s(ss)", sig=64, nativ="<=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @<=@ operator for comparison of 'Int' values.   ", op=98
        ),
        @Meta.SymV(
          offset=66686, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member=">"),
          stri="s(ss)", sig=64, nativ=">", pur=true, depth=2, rkind=33,
          doc=" Uses the java @>@ operator for comparison of 'Int' values.   ", op=98
        )
      }
    ),
    @Meta.SymI(
      offset=78115, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Double"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=6, lnks={},
      funs={
        @Meta.SymV(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="max"),
          stri="s(ss)", sig=43, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="min"),
          stri="s(ss)", sig=43, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=78205, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member=">="),
          stri="s(ss)", sig=66, nativ=">=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="compare"),
          stri="s(uu)", sig=67, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=78338, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<=>"),
          stri="s(ss)", sig=67, depth=2, rkind=49, op=97
        ),
        @Meta.SymV(
          offset=78253, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<"),
          stri="s(ss)", sig=66, nativ="<", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=78157, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<="),
          stri="s(ss)", sig=66, nativ="<=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=78301, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member=">"),
          stri="s(ss)", sig=66, nativ=">", pur=true, depth=2, rkind=33, op=98
        )
      }
    ),
    @Meta.SymI(
      offset=77106, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Float"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=48, lnks={},
      funs={
        @Meta.SymV(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="max"),
          stri="s(ss)", sig=50, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="min"),
          stri="s(ss)", sig=50, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=77193, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member=">="),
          stri="s(ss)", sig=68, nativ=">=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="compare"),
          stri="s(uu)", sig=69, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=77320, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<=>"),
          stri="s(ss)", sig=69, depth=2, rkind=49, op=97
        ),
        @Meta.SymV(
          offset=77239, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<"),
          stri="s(ss)", sig=68, nativ="<", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=77147, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<="),
          stri="s(ss)", sig=68, nativ="<=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=77285, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member=">"),
          stri="s(ss)", sig=68, nativ=">", pur=true, depth=2, rkind=33, op=98
        )
      }
    ),
    @Meta.SymI(
      offset=73779, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Bool"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=70, lnks={},
      funs={
        @Meta.SymV(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="max"),
          stri="s(su)", sig=71, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="min"),
          stri="s(su)", sig=71, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=73974, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member=">="),
          stri="s(su)", sig=71, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="compare"),
          stri="s(uu)", sig=72, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=74026, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<=>"),
          stri="s(su)", sig=72, depth=2, rkind=49, op=97
        ),
        @Meta.SymV(
          offset=73813, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<"),
          stri="s(su)", sig=71, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=73923, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<="),
          stri="s(su)", sig=71, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=73868, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member=">"),
          stri="s(su)", sig=71, depth=2, rkind=49, op=98
        )
      }
    ),
    @Meta.SymI(
      offset=75467, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Char"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=73, lnks={},
      funs={
        @Meta.SymV(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="min"),
          stri="s(ss)", sig=74, depth=2, rkind=49, doc="inherited from 'Ord.min'"
        ),
        @Meta.SymV(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="compare"),
          stri="s(uu)", sig=75, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="max"),
          stri="s(ss)", sig=74, depth=2, rkind=49, doc="inherited from 'Ord.max'"
        ),
        @Meta.SymV(
          offset=75713, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member=">"),
          stri="s(ss)", sig=76, nativ=">", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=75625, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member=">="),
          stri="s(ss)", sig=76, nativ=">=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=75669, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<"),
          stri="s(ss)", sig=76, nativ="<", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=75581, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<="),
          stri="s(ss)", sig=76, nativ="<=", pur=true, depth=2, rkind=33, op=98
        ),
        @Meta.SymV(
          offset=75496, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<=>"),
          stri="s(ss)", sig=75, depth=2, rkind=49, op=97
        )
      }
    ),
    @Meta.SymI(
      offset=60627, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord_Integer"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), typ=14, lnks={},
      funs={
        @Meta.SymV(
          offset=13708, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="min"),
          stri="s(ss)", sig=77, nativ="min", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=15028,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="hashCode"),
          stri="s(s)", sig=78, nativ="hashCode", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=13621, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="max"),
          stri="s(ss)", sig=77, nativ="max", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=60771, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member=">="),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=60845, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="=="),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=96
        ),
        @Meta.SymV(
          offset=60697, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member=">"),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=60627,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="compare"),
          stri="s(uu)", sig=80, depth=2, rkind=49, doc="inherited from 'Ord.compare'", op=97
        ),
        @Meta.SymV(
          offset=60808, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<="),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=60882, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="!="),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=96
        ),
        @Meta.SymV(
          offset=60734, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<"),
          stri="s(ss)", sig=79, depth=2, rkind=49, op=98
        ),
        @Meta.SymV(
          offset=60660, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<=>"),
          stri="s(ss)", sig=80, depth=2, rkind=49, op=97
        )
      },
      doc="" +"\n"+ " * 'Integer' is an instance of 'Ord'" +"\n"+ "      "
    ),
    @Meta.SymI(
      offset=68828, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), typ=56, lnks={},
      funs={
        @Meta.SymV(
          offset=69216, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="zero"),
          stri="s", sig=56, depth=0, rkind=49, doc=" The constant @0L@.   "
        ),
        @Meta.SymV(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="sign"),
          stri="s(s)", sig=81, depth=1, rkind=49, doc="inherited from 'Num.sign'"
        ),
        @Meta.SymV(
          offset=69257, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="one"),
          stri="s", sig=56, depth=0, rkind=49, doc=" The constant @1L@.   "
        ),
        @Meta.SymV(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="subtract"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Num.subtract'"
        ),
        @Meta.SymV(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isNumber"),
          stri="s(u)", sig=82, depth=1, rkind=49, doc="inherited from 'Num.isNumber'"
        ),
        @Meta.SymV(
          offset=68828,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isInfinite"), stri="s(u)",
          sig=82, depth=1, rkind=49, doc="inherited from 'Num.isInfinite'"
        ),
        @Meta.SymV(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isNaN"),
          stri="s(u)", sig=82, depth=1, rkind=49, doc="inherited from 'Num.isNaN'"
        ),
        @Meta.SymV(
          offset=69557, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="abs"),
          stri="s(s)", sig=83, depth=1, rkind=49,
          doc=" Returns the negated argument if it is negative, otherwise the argument itself." +"\n"+
          "" +"\n"+ "        This does not work for 'Long.minBound' since there is no corresponding positive" +"\n"+
          "        value that can be represented as a 'Long'. Rather" +"\n"+
          "        > abs Long.minBound == Long.minBound" +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=69998, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="fromInt"),
          stri="s(s)", sig=84, depth=1, rkind=49,
          doc=" applies the widening primitive conversion (JLS §5.1.2) from @int@ to @long@   "
        ),
        @Meta.SymV(
          offset=70021,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="fromInteger"),
          stri="s(s)", sig=85, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=69039, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="*"),
          stri="s(ss)", sig=57, nativ="*", pur=true, depth=2, rkind=33,
          doc=" Uses the java @\\*@ operator to multiply two 'Long' values.   ", op=69
        ),
        @Meta.SymV(
          offset=68929, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="+"),
          stri="s(ss)", sig=57, nativ="+", pur=true, depth=2, rkind=33,
          doc=" Uses the java @+@ operator to add two 'Long' values.   ", op=68
        ),
        @Meta.SymV(
          offset=69158, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="-"),
          stri="s(ss)", sig=57, nativ="-", pur=true, depth=2, rkind=33,
          doc=" Uses the java @-@ operator to subtract a 'Long' value from another.   ", op=68
        ),
        @Meta.SymV(
          offset=69883, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="negate"),
          stri="s(s)", sig=83, nativ="-", pur=true, depth=1, rkind=33,
          doc=" @negate a@ computes @0L-a@ using the java negation operator @-@." +"\n"+
          "" +"\n"+ "        This does not work for 'Long.minBound' since there is no corresponding positive" +"\n"+
          "        value that can be represented as a 'Long'. Rather" +"\n"+
          "        > negate Long.minBound == Long.minBound" +"\n"+ "         "
        )
      }
    ),
    @Meta.SymI(
      offset=66814, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), typ=16, lnks={},
      funs={
        @Meta.SymV(
          offset=67191, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="zero"),
          stri="s", sig=16, depth=0, rkind=49, doc=" the integer constant 0   "
        ),
        @Meta.SymV(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="sign"),
          stri="s(s)", sig=86, depth=1, rkind=49, doc="inherited from 'Num.sign'"
        ),
        @Meta.SymV(
          offset=67235, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="one"),
          stri="s", sig=16, depth=0, rkind=49, doc=" the integer constant 1   "
        ),
        @Meta.SymV(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="subtract"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Num.subtract'"
        ),
        @Meta.SymV(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isNumber"),
          stri="s(u)", sig=87, depth=1, rkind=49, doc="inherited from 'Num.isNumber'"
        ),
        @Meta.SymV(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isInfinite"),
          stri="s(u)", sig=87, depth=1, rkind=49, doc="inherited from 'Num.isInfinite'"
        ),
        @Meta.SymV(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isNaN"),
          stri="s(u)", sig=87, depth=1, rkind=49, doc="inherited from 'Num.isNaN'"
        ),
        @Meta.SymV(
          offset=67530, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="abs"),
          stri="s(s)", sig=86, depth=1, rkind=49,
          doc=" Returns the negated argument if it is negative, otherwise the argument itself." +"\n"+
          "" +"\n"+ "        This does not work for 'Int.minBound' since there is no corresponding positive" +"\n"+
          "        value that can be represented as an 'Int'. Rather" +"\n"+
          "        > abs Int.minBound == Int.minBound" +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=67942, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="fromInt"),
          stri="s(s)", sig=86, depth=1, rkind=49, expr=393, doc=" For 'Int' values, this is the identity function.   "
        ),
        @Meta.SymV(
          offset=67960,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="fromInteger"), stri="s(s)",
          sig=78, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=67015, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="*"),
          stri="s(ss)", sig=63, nativ="*", pur=true, depth=2, rkind=33,
          doc=" Uses the java @\\*@ operator to multiply 2 'Int' values.   ", op=69
        ),
        @Meta.SymV(
          offset=66911, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="+"),
          stri="s(ss)", sig=63, nativ="+", pur=true, depth=2, rkind=33,
          doc=" Uses the java @+@ operator to add 2 'Int' values.   ", op=68
        ),
        @Meta.SymV(
          offset=67132, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="-"),
          stri="s(ss)", sig=63, nativ="-", pur=true, depth=2, rkind=33,
          doc=" Uses the java @-@ operator to subtract one 'Int' value from another.   ", op=68
        ),
        @Meta.SymV(
          offset=67856, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="negate"),
          stri="s(s)", sig=86, nativ="-", pur=true, depth=1, rkind=33,
          doc=" @negate i@ computes @0-i@ using the java negation operator @-@." +"\n"+
          "" +"\n"+ "        This does not work for 'Int.minBound' since there is no corresponding positive" +"\n"+
          "        value that can be represented as an 'Int'. Rather" +"\n"+
          "        > negate Int.minBound == Int.minBound" +"\n"+ "         "
        )
      }
    ),
    @Meta.SymI(
      offset=73462, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), typ=56,
      lnks={},
      funs={
        @Meta.SymV(
          offset=73462,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="quotRem"),
          stri="s(uu)", sig=88, depth=2, rkind=49, doc="inherited from 'Integral.quotRem'"
        ),
        @Meta.SymV(
          offset=73507, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="rem"),
          stri="s(ss)", sig=57, nativ="%", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="powg"),
          stri="s(sss)", sig=90, depth=3, rkind=185, doc="inherited from 'Integral.powg'"
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="powf"),
          stri="s(ss)", sig=91, depth=2, rkind=185, doc="inherited from 'Integral.powf'"
        ),
        @Meta.SymV(
          offset=73558, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="quot"),
          stri="s(ss)", sig=57, nativ="/", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="mod"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Integral.mod'", op=69
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="lcm"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Integral.lcm'"
        ),
        @Meta.SymV(
          offset=73462,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="fromIntegral"),
          stri="s(u)", sig=92, depth=1, rkind=49, doc="inherited from 'Integral.fromIntegral'"
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="gcd"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Integral.gcd'"
        ),
        @Meta.SymV(
          offset=73462,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="gcdHelper"),
          stri="s(ss)", sig=57, depth=2, rkind=185, doc="inherited from 'Integral.gcdHelper'"
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="div"),
          stri="s(ss)", sig=57, depth=2, rkind=49, doc="inherited from 'Integral.div'", op=69
        ),
        @Meta.SymV(
          offset=73462,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="divMod"),
          stri="s(uu)", sig=88, depth=2, rkind=49, doc="inherited from 'Integral.divMod'"
        ),
        @Meta.SymV(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="^"),
          stri="s(ss)", sig=91, depth=2, rkind=49, doc="inherited from 'Integral.^'", op=87
        ),
        @Meta.SymV(
          offset=73677, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="big"),
          stri="s(s)", sig=93, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=73597, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="even"),
          stri="s(s)", sig=82, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=73637, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="odd"),
          stri="s(s)", sig=82, depth=1, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=73223, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), typ=16,
      lnks={},
      funs={
        @Meta.SymV(
          offset=73223,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="quotRem"),
          stri="s(uu)", sig=94, depth=2, rkind=49, doc="inherited from 'Integral.quotRem'"
        ),
        @Meta.SymV(
          offset=73267, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="rem"),
          stri="s(ss)", sig=63, nativ="%", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="powg"),
          stri="s(sss)", sig=95, depth=3, rkind=185, doc="inherited from 'Integral.powg'"
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="powf"),
          stri="s(ss)", sig=96, depth=2, rkind=185, doc="inherited from 'Integral.powf'"
        ),
        @Meta.SymV(
          offset=73314, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="quot"),
          stri="s(ss)", sig=63, nativ="/", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="mod"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Integral.mod'", op=69
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="lcm"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Integral.lcm'"
        ),
        @Meta.SymV(
          offset=73223,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="fromIntegral"),
          stri="s(u)", sig=97, depth=1, rkind=49, doc="inherited from 'Integral.fromIntegral'"
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="gcd"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Integral.gcd'"
        ),
        @Meta.SymV(
          offset=73223,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="gcdHelper"),
          stri="s(ss)", sig=63, depth=2, rkind=185, doc="inherited from 'Integral.gcdHelper'"
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="div"),
          stri="s(ss)", sig=63, depth=2, rkind=49, doc="inherited from 'Integral.div'", op=69
        ),
        @Meta.SymV(
          offset=73223,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="divMod"),
          stri="s(uu)", sig=94, depth=2, rkind=49, doc="inherited from 'Integral.divMod'"
        ),
        @Meta.SymV(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="^"),
          stri="s(ss)", sig=96, depth=2, rkind=49, doc="inherited from 'Integral.^'", op=87
        ),
        @Meta.SymV(
          offset=73429, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="big"),
          stri="s(s)", sig=98, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=73350, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="even"),
          stri="s(s)", sig=87, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=73389, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="odd"),
          stri="s(s)", sig=87, depth=1, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=62150, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral_Integer"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), typ=14,
      lnks={},
      funs={
        @Meta.SymV(
          offset=12395,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="zero"), stri="s",
          sig=14, nativ="java.math.BigInteger.ZERO", pur=true, depth=0, rkind=33
        ),
        @Meta.SymV(
          offset=14736,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="sign"),
          stri="s(s)", sig=78, nativ="signum", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="subtract"),
          stri="s(ss)", sig=77, depth=2, rkind=49, doc="inherited from 'Num.subtract'"
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="quotRem"),
          stri="s(uu)", sig=99, depth=2, rkind=49, doc="inherited from 'Integral.quotRem'"
        ),
        @Meta.SymV(
          offset=13120,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="rem"),
          stri="s(ss)", sig=77, nativ="remainder", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="powg"),
          stri="s(sss)", sig=100, depth=3, rkind=185, doc="inherited from 'Integral.powg'"
        ),
        @Meta.SymV(
          offset=12460,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="one"), stri="s",
          sig=14, nativ="java.math.BigInteger.ONE", pur=true, depth=0, rkind=33
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="powf"),
          stri="s(ss)", sig=101, depth=2, rkind=185, doc="inherited from 'Integral.powf'"
        ),
        @Meta.SymV(
          offset=13033,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="quot"),
          stri="s(ss)", sig=77, nativ="divide", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=13444,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="negate"),
          stri="s(s)", sig=102, nativ="negate", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="mod"),
          stri="s(ss)", sig=77, depth=2, rkind=49, doc="inherited from 'Integral.mod'", op=69
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isNumber"),
          stri="s(u)", sig=103, depth=1, rkind=49, doc="inherited from 'Num.isNumber'"
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="lcm"),
          stri="s(ss)", sig=77, depth=2, rkind=49, doc="inherited from 'Integral.lcm'"
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isInfinite"),
          stri="s(u)", sig=103, depth=1, rkind=49, doc="inherited from 'Num.isInfinite'"
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="gcdHelper"),
          stri="s(ss)", sig=77, depth=2, rkind=185, doc="inherited from 'Integral.gcdHelper'"
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromIntegral"),
          stri="s(u)", sig=104, depth=1, rkind=49, doc="inherited from 'Integral.fromIntegral'"
        ),
        @Meta.SymV(
          offset=62212,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromInteger"),
          stri="s(s)", sig=102, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=15100,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="gcd"),
          stri="s(ss)", sig=77, nativ="gcd", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isNaN"),
          stri="s(u)", sig=103, depth=1, rkind=49, doc="inherited from 'Num.isNaN'"
        ),
        @Meta.SymV(
          offset=62308,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="even"),
          stri="s(s)", sig=103, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="div"),
          stri="s(ss)", sig=77, depth=2, rkind=49, doc="inherited from 'Integral.div'", op=69
        ),
        @Meta.SymV(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="divMod"),
          stri="s(uu)", sig=99, depth=2, rkind=49, doc="inherited from 'Integral.divMod'"
        ),
        @Meta.SymV(
          offset=13368,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="abs"),
          stri="s(s)", sig=102, nativ="abs", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=12946, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="-"),
          stri="s(ss)", sig=77, nativ="subtract", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=12859, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="*"),
          stri="s(ss)", sig=77, nativ="multiply", pur=true, depth=2, rkind=33, op=69
        ),
        @Meta.SymV(
          offset=12772, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="+"),
          stri="s(ss)", sig=77, nativ="add", pur=true, depth=2, rkind=33, op=68
        ),
        @Meta.SymV(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="^"),
          stri="s(ss)", sig=101, depth=2, rkind=49, doc="inherited from 'Integral.^'", op=87
        ),
        @Meta.SymV(
          offset=62256,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="big"),
          stri="s(s)", sig=102, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=62186,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromInt"),
          stri="s(s)", sig=98, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=62351,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="odd"),
          stri="s(s)", sig=103, depth=1, rkind=49
        )
      },
      doc="" +"\n"+ " * 'Integer' is an instance of 'Integral'" +"\n"+ "      "
    ),
    @Meta.SymI(
      offset=74749, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_String"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=0, lnks={},
      funs={
        @Meta.SymV(
          offset=31193, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="hashCode"),
          stri="s(s)", sig=105, nativ="hashCode", pur=true, depth=1, rkind=33, doc=" get the hash code   "
        ),
        @Meta.SymV(
          offset=74833, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="!="),
          stri="s(ss)", sig=62, depth=2, rkind=49, op=96
        ),
        @Meta.SymV(
          offset=74790, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="=="),
          stri="s(ss)", sig=62, nativ="equals", pur=true, depth=2, rkind=33, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=79065, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_[]"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=106, lnks={},
      funs={
        @Meta.SymV(
          offset=79328, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="hashCode"),
          stri="s(s)", sig=108, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=79065, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="!="),
          stri="s(su)", sig=109, depth=2, rkind=49, doc="inherited from 'Eq.!='", op=96
        ),
        @Meta.SymV(
          offset=79195, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="=="),
          stri="s(su)", sig=109, depth=2, rkind=185,
          doc=" two lists are equal if their heads and tails are equal or if the lists are empty   ", op=96
        )
      }
    ),
    @Meta.SymI(
      offset=65992, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=16, lnks={},
      funs={
        @Meta.SymV(
          offset=12086, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="hashCode"),
          stri="s(s)", sig=86, depth=1, rkind=49, doc=" The 'hashCode' of an 'Int' is the identity   "
        ),
        @Meta.SymV(
          offset=66211, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="!="),
          stri="s(ss)", sig=64, nativ="!=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @!=@ operator for comparison of 'Int' values.   ", op=96
        ),
        @Meta.SymV(
          offset=66098, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="=="),
          stri="s(ss)", sig=64, nativ="==", pur=true, depth=2, rkind=33,
          doc=" Uses the java @==@ operator for comparison of 'Int' values.   ", op=96
        )
      }
    ),
    @Meta.SymI(
      offset=77991, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Double"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=6, lnks={},
      funs={
        @Meta.SymV(
          offset=38219, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="hashCode"),
          stri="s(s)", sig=42, depth=1, rkind=49,
          doc=" the 'hashCode' is that of the 'Long' value used to represent the 'Double'   "
        ),
        @Meta.SymV(
          offset=78081, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="!="),
          stri="s(ss)", sig=66, nativ="!=", pur=true, depth=2, rkind=33, op=96
        ),
        @Meta.SymV(
          offset=78033, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="=="),
          stri="s(ss)", sig=66, nativ="==", pur=true, depth=2, rkind=33, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=76989, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Float"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=48, lnks={},
      funs={
        @Meta.SymV(
          offset=36246, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="hashCode"),
          stri="s(s)", sig=49, nativ="java.lang.Float.floatToIntBits", pur=true, depth=1,
          rkind=33, doc=" bit representation of a float serves as hashCode   "
        ),
        @Meta.SymV(
          offset=77075, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="!="),
          stri="s(ss)", sig=68, nativ="!=", pur=true, depth=2, rkind=33, op=96
        ),
        @Meta.SymV(
          offset=77029, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="=="),
          stri="s(ss)", sig=68, nativ="==", pur=true, depth=2, rkind=33, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=57486, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_()"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=110, lnks={},
      funs={
        @Meta.SymV(
          offset=57552, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="hashCode"),
          stri="s(s)", sig=111, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=57514, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="!="),
          stri="s(ss)", sig=112, depth=2, rkind=49, op=96
        ),
        @Meta.SymV(
          offset=57535, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="=="),
          stri="s(ss)", sig=112, depth=2, rkind=49, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=5866, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Bool"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=70, lnks={},
      funs={
        @Meta.SymV(
          offset=5979, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="hashCode"),
          stri="s(s)", sig=113, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=5948, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="!="),
          stri="s(ss)", sig=71, nativ="!=", pur=true, depth=2, rkind=33, op=96
        ),
        @Meta.SymV(
          offset=5905, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="=="),
          stri="s(ss)", sig=71, nativ="==", pur=true, depth=2, rkind=33, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=75355, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Char"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=73, lnks={},
      funs={
        @Meta.SymV(
          offset=17989, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="hashCode"),
          stri="s(s)", sig=114, nativ="(int)", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=75438, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="!="),
          stri="s(ss)", sig=76, nativ="!=", pur=true, depth=2, rkind=33, op=96
        ),
        @Meta.SymV(
          offset=75395, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="=="),
          stri="s(ss)", sig=76, nativ="==", pur=true, depth=2, rkind=33, op=96
        )
      }
    ),
    @Meta.SymI(
      offset=67984, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), typ=56, lnks={},
      funs={
        @Meta.SymV(
          offset=17331, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="hashCode"),
          stri="s(s)", sig=81, depth=1, rkind=49, doc=" hash code is upper half and lower half xor'ed   "
        ),
        @Meta.SymV(
          offset=68208, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="!="),
          stri="s(ss)", sig=58, nativ="!=", pur=true, depth=2, rkind=33,
          doc=" Uses the java @!=@ operator for comparison of 'Long' values.   ", op=96
        ),
        @Meta.SymV(
          offset=68092, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="=="),
          stri="s(ss)", sig=58, nativ="==", pur=true, depth=2, rkind=33,
          doc=" Uses the java @==@ operator for comparison of 'Long' values.   ", op=96
        )
      }
    ),
    @Meta.SymI(
      offset=71921, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), typ=56, lnks={},
      funs={
        @Meta.SymV(
          offset=72446, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="succ"),
          stri="s(s)", sig=83, depth=1, rkind=49,
          doc=" @succ a@ is the same as @a+1L@ except for @succ Long.maxBound@, which is 'undefined'   "
        ),
        @Meta.SymV(
          offset=72257, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="pred"),
          stri="s(s)", sig=83, depth=1, rkind=49,
          doc=" @pred a@ is the same as @a-1L@ except for @pred Long.minBound@, which is 'undefined'   "
        ),
        @Meta.SymV(
          offset=72134, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="from"),
          stri="s(s)", sig=84, depth=1, rkind=49,
          doc=" @Long.from i@ returns a 'Long' with the same numeric value as @i@.   "
        ),
        @Meta.SymV(
          offset=72783,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromThenTo"),
          stri="s(sss)", sig=115, depth=3, rkind=49
        ),
        @Meta.SymV(
          offset=72657, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFrom"),
          stri="s(s)", sig=116, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=72702,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromThen"),
          stri="s(ss)", sig=117, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=72541,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromTo"),
          stri="s(ss)", sig=117, depth=2, rkind=177
        ),
        @Meta.SymV(
          offset=72030, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="ord"),
          stri="s(s)", sig=81, depth=1, rkind=49,
          doc=" @ord l@ is only valid if @Int.minBound.long <= l && l <= Int.maxBound@   "
        )
      }
    ),
    @Meta.SymI(
      offset=60960, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Integer"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), typ=14, lnks={},
      funs={
        @Meta.SymV(
          offset=61037, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="succ"),
          stri="s(s)", sig=102, depth=1, rkind=49, doc=" @succ b@  is the same as @b + 1.big@   "
        ),
        @Meta.SymV(
          offset=61239, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="ord"),
          stri="s(s)", sig=78, depth=1, rkind=49,
          doc=" @ord b@ is only defined if the value of b is in the range 'Int.minBound' .. 'Int.maxBound'   "
        ),
        @Meta.SymV(
          offset=61111, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="pred"),
          stri="s(s)", sig=102, depth=1, rkind=49, doc=" @succ b@  is the same as @b + 1.big@   "
        ),
        @Meta.SymV(
          offset=61660,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromThenTo"),
          stri="s(sss)", sig=118, depth=3, rkind=49
        ),
        @Meta.SymV(
          offset=61329,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromTo"),
          stri="s(ss)", sig=119, depth=2, rkind=177
        ),
        @Meta.SymV(
          offset=61445,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFrom"),
          stri="s(s)", sig=120, depth=1, rkind=177
        ),
        @Meta.SymV(
          offset=61492,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromThen"),
          stri="s(ss)", sig=119, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=61310, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="from"),
          stri="s(s)", sig=98, depth=1, rkind=49, doc=" @Integer.from i@ is the same as @Int.big i@   "
        )
      },
      doc="" +"\n"+ " * 'Integer' is an instance of 'Enum'" +"\n"+ "      "
    ),
    @Meta.SymI(
      offset=75902, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Char"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), typ=73, lnks={},
      funs={
        @Meta.SymV(
          offset=76022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="succ"),
          stri="s(s)", sig=121, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=17906, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="ord"),
          stri="s(s)", sig=114, nativ="(int)", pur=true, depth=1, rkind=33,
          doc="" +"\n"+ "     * @c.ord@ is the ordinal (integer) value of the character @c@." +"\n"+
          "     * It holds: @c.ord.char@ == @c@, see 'Int.char'." +"\n"+
          "     * (But note that @i.char.ord@ is not necessarily @i@)" +"\n"+ "          "
        ),
        @Meta.SymV(
          offset=75943, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="from"),
          stri="s(s)", sig=122, nativ="(char)", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=75982, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="pred"),
          stri="s(s)", sig=121, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=76304,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromThenTo"),
          stri="s(sss)", sig=123, depth=3, rkind=49
        ),
        @Meta.SymV(
          offset=76178, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFrom"),
          stri="s(s)", sig=124, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=76223,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromThen"),
          stri="s(ss)", sig=125, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=76062,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromTo"),
          stri="s(ss)", sig=125, depth=2, rkind=177
        )
      }
    ),
    @Meta.SymI(
      offset=74167, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Bool"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), typ=70, lnks={},
      funs={
        @Meta.SymV(
          offset=74361, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="succ"),
          stri="s(s)", sig=126, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=74304, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="pred"),
          stri="s(s)", sig=126, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=74232, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="from"),
          stri="s(s)", sig=87, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=74585,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromThenTo"),
          stri="s(ssu)", sig=127, depth=3, rkind=49
        ),
        @Meta.SymV(
          offset=74630, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFrom"),
          stri="s(s)", sig=128, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=74510,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromThen"),
          stri="s(ss)", sig=129, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=74418,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromTo"),
          stri="s(su)", sig=129, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=74196, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="ord"),
          stri="s(s)", sig=113, depth=1, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=70513, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), typ=16, lnks={},
      funs={
        @Meta.SymV(
          offset=70902, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="succ"),
          stri="s(s)", sig=86, depth=1, rkind=49,
          doc=" @succ i@ is the same as @i+1@ except for @succ Int.maxBound@, which is 'undefined'   "
        ),
        @Meta.SymV(
          offset=70718, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="pred"),
          stri="s(s)", sig=86, depth=1, rkind=49,
          doc=" @pred i@ is the same as @i-1@ except for @pred Int.minBound@, which is 'undefined'   "
        ),
        @Meta.SymV(
          offset=70605, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="from"),
          stri="s(s)", sig=86, depth=1, rkind=49, doc=" > from i = i   "
        ),
        @Meta.SymV(
          offset=71236,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromThenTo"),
          stri="s(sss)", sig=130, depth=3, rkind=49
        ),
        @Meta.SymV(
          offset=71110, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFrom"),
          stri="s(s)", sig=131, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=71155,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromThen"),
          stri="s(ss)", sig=132, depth=2, rkind=49
        ),
        @Meta.SymV(
          offset=70994,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromTo"),
          stri="s(ss)", sig=132, depth=2, rkind=177
        ),
        @Meta.SymV(
          offset=70562, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="ord"),
          stri="s(s)", sig=86, depth=1, rkind=49, doc=" > ord i = i   "
        )
      }
    ),
    @Meta.SymI(
      offset=71681, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Long"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), typ=56,
      lnks={},
      funs={
        @Meta.SymV(
          offset=71888,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Long", member="maxBound"), stri="s",
          sig=56, depth=0, rkind=49, doc=" the largest 'Long' value  9223372036854775807 (or (2**63)-1)   "
        ),
        @Meta.SymV(
          offset=71783,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Long", member="minBound"), stri="s",
          sig=56, depth=0, rkind=49, doc=" the smallest 'Long' value  -9223372036854775808 (or -(2**63))   "
        )
      }
    ),
    @Meta.SymI(
      offset=75743, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Char"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), typ=73,
      lnks={},
      funs={
        @Meta.SymV(
          offset=75852,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Char", member="maxBound"), stri="s",
          sig=73, nativ="java.lang.Character.MAX_VALUE", pur=true, depth=0, rkind=33
        ),
        @Meta.SymV(
          offset=75787,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Char", member="minBound"), stri="s",
          sig=73, nativ="java.lang.Character.MIN_VALUE", pur=true, depth=0, rkind=33
        )
      }
    ),
    @Meta.SymI(
      offset=74093, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Bool"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), typ=70,
      lnks={},
      funs={
        @Meta.SymV(
          offset=74148,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Bool", member="maxBound"), stri="s",
          sig=70, depth=0, rkind=49
        ),
        @Meta.SymV(
          offset=74125,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Bool", member="minBound"), stri="s",
          sig=70, depth=0, rkind=49
        )
      }
    ),
    @Meta.SymI(
      offset=70310, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded_Int"),
      clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), typ=16,
      lnks={},
      funs={
        @Meta.SymV(
          offset=70487,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Int", member="maxBound"), stri="s",
          sig=16, depth=0, rkind=49, doc=" the largest 'Int' value  2147483647 (or (2**31)-1)   "
        ),
        @Meta.SymV(
          offset=70401,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Int", member="minBound"), stri="s",
          sig=16, depth=0, rkind=49, doc=" the smallest 'Int' value  -2147483648 (or -(2**31))   "
        )
      }
    )
  },
  symts={
    @Meta.SymT(
      offset=46349, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Undefined"), typ=133,
      kind=3, cons={}, lnks={},
      funs={
        @Meta.SymV(
          offset=46603, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="newβ"),
          stri="s(ss)", sig=135, nativ="new", pur=true, depth=2, rkind=33, publik=false,
          doc=" " +"\n"+ "        create an 'Undefined' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             "
        ),
        @Meta.SymV(
          offset=46824, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="newX"),
          stri="s(s)", sig=136, depth=1, rkind=49,
          doc=" create an 'Undefined' value from a cause ('Throwable'). " +"\n"+
          "        The message will be taken from the cause." +"\n"+ "              "
        ),
        @Meta.SymV(
          offset=46603, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="newα"),
          stri="s(s)", sig=137, nativ="new", pur=true, depth=1, rkind=33, publik=false,
          doc=" " +"\n"+ "        create an 'Undefined' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             "
        ),
        @Meta.SymV(
          offset=47124, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="die"),
          stri="s(s)", sig=138, nativ="die", pur=true, depth=1, rkind=33,
          doc=" Throw this 'Undefined', this will abort the computation evaluating it.   " +"\n"+
          "" +"\n"+ " Actually, the return type is not correct, since it never returns.   "
        ),
        @Meta.SymV(
          offset=46603, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="new"),
          stri="s", sig=139, nativ="new", pur=true, depth=0, rkind=33,
          doc=" " +"\n"+ "        create an 'Undefined' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="newα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Undefined", member="newβ")
          }
        )
      },
      pur=true, nativ="frege.runtime.Undefined",
      doc=" An unchecked subclass of @java.lang.RuntimeException@ for the Frege Runtime   " +"\n"+
      "" +"\n"+ " An instance of 'Undefined' will be thrown if 'undefined' or ('error' msg) is evaluated.     "
    ),
    @Meta.SymT(
      offset=26887, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="StringJ"), typ=140,
      kind=61, cons={},
      lnks={
        @Meta.SymL(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="min")
        ),
        @Meta.SymL(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="max")
        ),
        @Meta.SymL(
          offset=31193, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="hashCode")
        ),
        @Meta.SymL(
          offset=74887, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="compare")
        ),
        @Meta.SymL(
          offset=74960, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member=">")
        ),
        @Meta.SymL(
          offset=75036, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member=">=")
        ),
        @Meta.SymL(
          offset=74918, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<=>")
        ),
        @Meta.SymL(
          offset=74998, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<")
        ),
        @Meta.SymL(
          offset=75074, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_String", member="<=")
        ),
        @Meta.SymL(
          offset=74833, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="!=")
        ),
        @Meta.SymL(
          offset=74790, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_String", member="==")
        )
      },
      funs={
        @Meta.SymV(
          offset=33544, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="wait"),
          stri="s(s)", sig=141, nativ="frege.run.Concurrent.waitFor", depth=1, rkind=33,
          doc=" wait on 'Object's monitor   ", throwing={48}
        ),
        @Meta.SymV(
          offset=33457, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="trim"),
          stri="s(s)", sig=142, nativ="trim", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=30664,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="toLowerCase"), stri="s(s)",
          sig=142, nativ="toLowerCase", pur=true, depth=1, rkind=33, doc=" convert to lower case   "
        ),
        @Meta.SymV(
          offset=30743,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="toUpperCase"), stri="s(s)",
          sig=142, nativ="toUpperCase", pur=true, depth=1, rkind=33, doc=" convert to upper case   "
        ),
        @Meta.SymV(
          offset=33307, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="replace"),
          stri="s(sss)", sig=143, nativ="replace", pur=true, depth=3, rkind=33,
          doc=" replace a character in a string   "
        ),
        @Meta.SymV(
          offset=31396, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="startsWith"),
          stri="s(ss)", sig=62, nativ="startsWith", pur=true, depth=2, rkind=33,
          doc=" true if the second string is a prefix of the first one   "
        ),
        @Meta.SymV(
          offset=30374, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="quote"),
          stri="s(s)", sig=142, nativ="java.util.regex.Pattern.quote", pur=true, depth=1,
          rkind=33, doc=" quote regular expression metacharacters in string   "
        ),
        @Meta.SymV(
          offset=30509,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="quoteReplacement"),
          stri="s(s)", sig=142, nativ="java.util.regex.Matcher.quoteReplacement", pur=true,
          depth=1, rkind=33, doc=" quote replacement string metacharacters in string   "
        ),
        @Meta.SymV(
          offset=31256, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="substr"),
          stri="s(sss)", sig=145, nativ="substring", pur=true, depth=3, rkind=33, doc=" see 'substr'   "
        ),
        @Meta.SymV(
          offset=33824, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="notifyAll"),
          stri="s(s)", sig=141, nativ="frege.run.Concurrent.notifyAll", depth=1, rkind=33,
          doc=" notify all threads waiting on the objects monitor   "
        ),
        @Meta.SymV(
          offset=28613, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="long"),
          stri="s(s)", sig=146, nativ="java.lang.Long.parseLong", pur=true, depth=1, rkind=33,
          doc=" Safe way to parse a long integer from a string. See 'String.int'   "
        ),
        @Meta.SymV(
          offset=33698, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="notify"),
          stri="s(s)", sig=141, nativ="frege.run.Concurrent.notifyOne", depth=1, rkind=33,
          doc=" notify a single thread waiting on the objects monitor   "
        ),
        @Meta.SymV(
          offset=32753,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfδ"),
          stri="s(sss)", sig=147, nativ="lastIndexOf", pur=true, depth=3, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=32753,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfβ"),
          stri="s(sss)", sig=148, nativ="lastIndexOf", pur=true, depth=3, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=32753,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfγ"),
          stri="s(ss)", sig=149, nativ="lastIndexOf", pur=true, depth=2, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=26981, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="length"),
          stri="s(s)", sig=150, nativ="length", pur=true, depth=1, rkind=33, doc=" The length of a 'String'   "
        ),
        @Meta.SymV(
          offset=32753,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOf"), stri="s",
          sig=139, nativ="lastIndexOf", pur=true, depth=0, rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfβ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfγ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfδ")
          }
        ),
        @Meta.SymV(
          offset=31500,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="intToString"), stri="s(s)",
          sig=151, nativ="java.lang.String.valueOf", pur=true, depth=1, rkind=33,
          doc=" convert an 'Int' to a 'String'   "
        ),
        @Meta.SymV(
          offset=29133, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="integer"),
          stri="s(s)", sig=152, nativ="new", pur=true, depth=1, rkind=33,
          doc=" Safe way to parse a big 'Integer' value from a string. See 'String.int'   "
        ),
        @Meta.SymV(
          offset=32699, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="isEmpty"),
          stri="s(s)", sig=153, nativ="isEmpty", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=32753,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="lastIndexOfα"),
          stri="s(ss)", sig=154, nativ="lastIndexOf", pur=true, depth=2, rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=29941,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="polymorphicElemAt"),
          stri="s(ss)", sig=155, nativ="PreludeBase.polyCharAt", pur=true, depth=2, rkind=33,
          doc=" Retrieve element of 'String' at index" +"\n"+ "    " +"\n"+
          "        Because it is impossible to create a 'String' from anything but 'Char's," +"\n"+
          "        the type is not even wrong." +"\n"+ "        " +"\n"+
          "        Will be needed in the 'String' instance of 'frege.prelude.PreludeList#ListLike'" +"\n"+
          "        that expects a type with kind @*->*@." +"\n"+ "         ",
          gargs={22}
        ),
        @Meta.SymV(
          offset=29405, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfδ"),
          stri="s(sss)", sig=147, nativ="indexOf", pur=true, depth=3, rkind=33, publik=false,
          doc=" > str.indexOf ch   " +"\n"+ "" +"\n"+ " the first index of _ch_ in _str_, or -1 if not present   "
        ),
        @Meta.SymV(
          offset=29405, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfβ"),
          stri="s(sss)", sig=148, nativ="indexOf", pur=true, depth=3, rkind=33, publik=false,
          doc=" > str.indexOf ch   " +"\n"+ "" +"\n"+ " the first index of _ch_ in _str_, or -1 if not present   "
        ),
        @Meta.SymV(
          offset=29405, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfα"),
          stri="s(ss)", sig=154, nativ="indexOf", pur=true, depth=2, rkind=33, publik=false,
          doc=" > str.indexOf ch   " +"\n"+ "" +"\n"+ " the first index of _ch_ in _str_, or -1 if not present   "
        ),
        @Meta.SymV(
          offset=29405, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfγ"),
          stri="s(ss)", sig=149, nativ="indexOf", pur=true, depth=2, rkind=33, publik=false,
          doc=" > str.indexOf ch   " +"\n"+ "" +"\n"+ " the first index of _ch_ in _str_, or -1 if not present   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatι"),
          stri="s(ssssssssss)", sig=162, nativ="java.lang.String.format", pur=true, depth=10,
          rkind=33, publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatη"),
          stri="s(ssssssss)", sig=163, nativ="java.lang.String.format", pur=true, depth=8,
          rkind=33, publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatθ"),
          stri="s(sssssssss)", sig=164, nativ="java.lang.String.format", pur=true, depth=9,
          rkind=33, publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatε"),
          stri="s(ssssss)", sig=165, nativ="java.lang.String.format", pur=true, depth=6,
          rkind=33, publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatγ"),
          stri="s(ssss)", sig=166, nativ="java.lang.String.format", pur=true, depth=4, rkind=33,
          publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatδ"),
          stri="s(sssss)", sig=167, nativ="java.lang.String.format", pur=true, depth=5, rkind=33,
          publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatα"),
          stri="s(ss)", sig=168, nativ="java.lang.String.format", pur=true, depth=2, rkind=33,
          publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=28783, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="float"),
          stri="s(s)", sig=169, nativ="java.lang.Float.parseFloat", pur=true, depth=1, rkind=33,
          doc=" Safe way to parse a 'Float' value from a string. See 'String.int'   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="format"),
          stri="s", sig=139, nativ="java.lang.String.format", pur=true, depth=0, rkind=33,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatβ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatγ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatδ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatε"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatζ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatη"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatθ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatι")
          }
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatβ"),
          stri="s(sss)", sig=170, nativ="java.lang.String.format", pur=true, depth=3, rkind=33,
          publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=31709, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="formatζ"),
          stri="s(sssssss)", sig=171, nativ="java.lang.String.format", pur=true, depth=7,
          rkind=33, publik=false,
          doc=" Format 1 to 9 values   " +"\n"+ "" +"\n"+
          " May throw @IllegalFormatException@, if the type of any argument does not fit the format specifier.   "
        ),
        @Meta.SymV(
          offset=29405, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOf"),
          stri="s", sig=139, nativ="indexOf", pur=true, depth=0, rkind=33,
          doc=" > str.indexOf ch   " +"\n"+ "" +"\n"+ " the first index of _ch_ in _str_, or -1 if not present   ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfβ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfγ"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="indexOfδ")
          }
        ),
        @Meta.SymV(
          offset=32635,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="contentEquals"),
          stri="s(ss)", sig=62, nativ="contentEquals", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=32571, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="contains"),
          stri="s(ss)", sig=62, nativ="contains", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=30957,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="compareToIgnoreCase"),
          stri="s(ss)", sig=149, nativ="compareToIgnoreCase", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=32505,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="concatenate"),
          stri="s(ss)", sig=60, nativ="concat", pur=true, depth=2, rkind=33,
          doc=" This is the native String.concat, just renamed because naming conflicts   "
        ),
        @Meta.SymV(
          offset=32298,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="codePointBefore"),
          stri="s(ss)", sig=172, nativ="codePointBefore", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=32358,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="codePointCount"),
          stri="s(sss)", sig=173, nativ="codePointCount", pur=true, depth=3, rkind=33
        ),
        @Meta.SymV(
          offset=27983, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="aton"),
          stri="s(s)", sig=174, nativ="new", pur=true, depth=1, rkind=33,
          doc=" Like 'String.integer', but the exception is not checked, thus only good when one *knows for sure* that the parse will succeed.      "
        ),
        @Meta.SymV(
          offset=29268, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="charAt"),
          stri="s(ss)", sig=175, nativ="charAt", pur=true, depth=2, rkind=33, doc=" retrieve character at index   "
        ),
        @Meta.SymV(
          offset=32238,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="codePointAt"),
          stri="s(ss)", sig=172, nativ="codePointAt", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=30861, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="compareTo"),
          stri="s(ss)", sig=149, nativ="compareTo", pur=true, depth=2, rkind=33,
          doc=" 'String.compareTo' is used in the 'Ord' instance of 'String'   "
        ),
        @Meta.SymV(
          offset=27158, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="atoi"),
          stri="s(s)", sig=105, nativ="java.lang.Integer.parseInt", pur=true, depth=1, rkind=33,
          doc=" Like 'String.int', but the exception is not checked, thus only good when one *knows for sure* that the parse will succeed.      "
        ),
        @Meta.SymV(
          offset=27773, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="atod"),
          stri="s(s)", sig=176, nativ="java.lang.Double.parseDouble", pur=true, depth=1,
          rkind=33,
          doc=" Like 'String.double', but the exception is not checked, thus only good when one *knows for sure* that the parse will succeed.      "
        ),
        @Meta.SymV(
          offset=27566, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="atof"),
          stri="s(s)", sig=177, nativ="java.lang.Float.parseFloat", pur=true, depth=1, rkind=33,
          doc=" Like 'String.float', but the exception is not checked, thus only good when one *knows for sure* that the parse will succeed.      "
        ),
        @Meta.SymV(
          offset=31079, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="++"),
          stri="s(ss)", sig=178, nativ="+", pur=true, depth=2, rkind=33,
          doc=" Concatenate two strings, uses Java's @+@ operator   ", op=85
        ),
        @Meta.SymV(
          offset=27361, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="atol"),
          stri="s(s)", sig=179, nativ="java.lang.Long.parseLong", pur=true, depth=1, rkind=33,
          doc=" Like 'String.long', but the exception is not checked, thus only good when one *knows for sure* that the parse will succeed.      "
        ),
        @Meta.SymV(
          offset=28955, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="double"),
          stri="s(s)", sig=180, nativ="java.lang.Double.parseDouble", pur=true, depth=1,
          rkind=33, doc=" Safe way to parse a 'Double' value from a string. See 'String.int'   "
        ),
        @Meta.SymV(
          offset=28445, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="StringJ", member="int"),
          stri="s(s)", sig=181, nativ="java.lang.Integer.parseInt", pur=true, depth=1, rkind=33,
          doc=" Safe way to parse an integer from a string." +"\n"+
          "        @java.lang.NumberFormatException@ will be caught and returned as 'Left' value." +"\n"+
          "        When the parse succeeds, the integer is returned in the 'Right' value." +"\n"+
          "" +"\n"+ "        Use like this:" +"\n"+ "        > case s.int of" +"\n"+
          "        >   Left exc -> ...     -- s is not well formed" +"\n"+
          "        >   Right i  -> ...     -- the parsed value is in i" +"\n"+ "         "
        )
      },
      pur=true, nativ="java.lang.String", publik=false,
      doc="" +"\n"+ "    For technical reasons, the native string type is defined with a phantom type," +"\n"+
      "    which allows us to treat strings like character lists." +"\n"+ "    " +"\n"+
      "    The following rules apply:" +"\n"+
      "    - There must be no polymorphic non empty string. Trying to extract elements from it" +"\n"+
      "      with 'String.charAt' would fail with an exception at runtime." +"\n"+
      "    - Every function with return type ('StringJ' a) must either take one or more arguments" +"\n"+
      "      of the same type which it manipulates, or it must return the empty string. In the former" +"\n"+
      "      case, the elements of the result string must all be computed from the " +"\n"+
      "      elements of the argument strings.    " +"\n"+ "         "
    ),
    @Meta.SymT(
      offset=40083, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Throwable"), typ=134,
      kind=3, cons={}, lnks={},
      funs={
        @Meta.SymV(
          offset=40267,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Throwable", member="printStackTrace"),
          stri="s(s)", sig=182, nativ="printStackTrace", depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=40364, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Throwable", member="caught"),
          stri="s(s)", sig=183, depth=1, rkind=49, doc=" give the class name of this exception   "
        ),
        @Meta.SymV(
          offset=40149,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Throwable", member="getLocalizedMessage"),
          stri="s(s)", sig=183, nativ="getLocalizedMessage", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=40208,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Throwable", member="getMessage"),
          stri="s(s)", sig=183, nativ="getMessage", pur=true, depth=1, rkind=33
        )
      },
      pur=true, nativ="java.lang.Throwable", publik=false, doc=" Frege wrapper for java Throwables.   "
    ),
    @Meta.SymT(
      offset=84571, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="ST"), typ=184,
      kind=76,
      cons={
        @Meta.SymD(
          offset=84580, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member="ST"), cid=0,
          typ=187, fields={@Meta.Field(offset=0, sigma=186, strict=false)}, priv=true, publik=false
        )
      },
      lnks={},
      funs={
        @Meta.SymV(
          offset=85336, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member="return"),
          stri="s(u)", sig=189, depth=1, rkind=49
        ),
        @Meta.SymV(
          offset=85044, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member="run"),
          stri="s(s)", sig=191, depth=1, rkind=52,
          doc=" nowarn: performUnsafe" +"\n"+ "    " +"\n"+
          "     Run a stateful action with type @ST r a@ and return a result of type _a_." +"\n"+
          "     The overall computation @ST.run st@ is pure, though inside the 'ST' action" +"\n"+
          "     local mutable state can be employed." +"\n"+ "     " +"\n"+
          "     This is possible only if the result type @a@ of the state action does *not* mention" +"\n"+
          "     @r@ and if @r@ is a type variable. Hence, it is not possible to 'ST.run' an 'IO' action." +"\n"+
          "          "
        ),
        @Meta.SymV(
          offset=85373, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member=">>="),
          stri="s(s(s)s)", sig=194, depth=2, rkind=49, expr=411, op=58
        ),
        @Meta.SymV(
          offset=85248, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member="performUnsafe"),
          stri="s(s(s))", sig=196, depth=1, rkind=52,
          doc=" warning: You are breaking the rules. Expect an arbitrary result and program crashes.   "
        )
      },
      prod=true, newt=true,
      doc="" +"\n"+ "  @(ST s a)@ is an abstract data type and is" +"\n"+
      "  a computation that encapsulates side effects in state thread @s@" +"\n"+ "  and returns a value of type @a@."
      +"\n"+ "" +"\n"+ "  The type @s@ can be understood as a compiler generated unique index for state threads." +"\n"+
      "  Every state thread is independent of each other and keeps track of mutable variables" +"\n"+
      "  created in it. For detailed information, read the paper _Lazy Functional State Threads_." +"\n"+
      "" +"\n"+ "  Every mutable native data type shall be wrapped in a 'frege.prelude.PreludeIO#Mutable' " +"\n"+
      "  with phantom type parameter @s@." +"\n"+ "  that tells to what state thread the value belongs. " +"\n"+
      "  For example, the @new@ method of" +"\n"+ "  the java class @java.util.Date@ could be accessed like this:"
      +"\n"+ "" +"\n"+ "  > data Date s = native java.util.Date where" +"\n"+
      "  >     native new :: Long -> ST s (Mutable s Date)" +"\n"+ "" +"\n"+
      "  Inside ST actions, Date values can be created and manipulated with" +"\n"+
      "  impure native methods at will. However, such a value can never escape" +"\n"+
      "  its ST thread." +"\n"+ "" +"\n"+
      "  Because @ST s@ is an instance of 'frege.prelude.PreludeMonad#Monad', ST actions can be combined, which" +"\n"+
      "  ensures sequenced execution. For example, we could add another method" +"\n"+
      "  to the Date type that converts the date to a string:" +"\n"+
      "  >     native toString :: Mutable s Date -> ST s String" +"\n"+ "  " +"\n"+
      "  and a computation which yields the current time in string form:" +"\n"+ "  "
      +"\n"+ "  > epoch = do" +"\n"+ "  >    date <- Date.new 0L" +"\n"+ "  >    return date.toString" +"\n"+
      "  " +"\n"+ "  This looks almost like java already! @epoch@ has type @ST s String@ and we can run" +"\n"+
      "  the computation with @epoch.run@ (see 'ST.run' below), " +"\n"+ "  which gives us a nice, pure, immutable,"
      +"\n"+ "  functional, correct 'String' value." +"\n"+ "" +"\n"+
      "  The 'IO' type is just an alias for 'ST' 'RealWorld', and can be thought of as" +"\n"+
      "  indexing a unique global state thread." +"\n"+ "  Values of type 'IO' @a@ are also called  _IO actions_."
      +"\n"+ "" +"\n"+ "  Any ST value can also be used in the IO thread." +"\n"+ ""
      +"\n"+ "  This guarantees that" +"\n"+ "  - any computation with side effect is sequenced through the ST-Monad"
      +"\n"+ "  - any function whose return type is not @IO something@ does not have side effects," +"\n"+
      "    as long as no impure native function or value is deliberately declared to be pure." +"\n"+
      "" +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=85734, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="RealWorld"), typ=197,
      kind=3, cons={}, lnks={}, funs={}, pur=true, nativ="frege.runtime.Phantom.RealWorld",
      doc="" +"\n"+ " * This abstract data type identifies the global state (disk, network, you name it)." +"\n"+
      " * Values of type 'ST' 'RealWorld' @a@ are likely to perform input/output, manipulate" +"\n"+
      " * global data and so on." +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=52181, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ordering"), typ=198,
      kind=3,
      cons={
        @Meta.SymD(
          offset=52192, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Lt"), cid=0,
          typ=198, fields={}
        ),
        @Meta.SymD(
          offset=52197, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Eq"), cid=1,
          typ=198, fields={}
        ),
        @Meta.SymD(
          offset=52202, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Gt"), cid=2,
          typ=198, fields={}
        )
      },
      lnks={}, funs={}, isEnum=true, doc=" 'Ordering' encodes the results of comparisons, see also  '<=>'   "
    ),
    @Meta.SymT(
      offset=38642, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Object"), typ=199,
      kind=3, cons={}, lnks={},
      funs={
        @Meta.SymV(
          offset=39148, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Object", member="notifyAll"),
          stri="s(s)", sig=200, nativ="frege.run.Concurrent.notifyAll", depth=1, rkind=33,
          doc=" notify all threads waiting on the objects monitor   "
        ),
        @Meta.SymV(
          offset=38860, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Object", member="wait"),
          stri="s(s)", sig=200, nativ="frege.run.Concurrent.waitFor", depth=1, rkind=33,
          doc=" wait on 'Object's monitor   ", throwing={48}
        ),
        @Meta.SymV(
          offset=38753, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Object", member="getClass"),
          stri="s(s)", sig=201, nativ="frege.runtime.Runtime.getClass", pur=true, depth=1,
          rkind=33, doc=" for any value, we can get its @Java@ class   ", gargs={22}
        ),
        @Meta.SymV(
          offset=39022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Object", member="notify"),
          stri="s(s)", sig=200, nativ="frege.run.Concurrent.notifyOne", depth=1, rkind=33,
          doc=" notify a single thread waiting on the objects monitor   "
        )
      },
      pur=true, nativ="java.lang.Object", publik=false, doc="" +"\n"+ "    The Java Object type" +"\n"+ "         "
    ),
    @Meta.SymT(
      offset=47612, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="NumberFormatException"), typ=202,
      kind=3, cons={}, lnks={},
      funs={
        @Meta.SymV(
          offset=47898,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="NumberFormatException", member="newβ"),
          stri="s(ss)", sig=203, nativ="new", pur=true, depth=2, rkind=33, publik=false,
          doc=" " +"\n"+ "        create an 'NumberFormatException' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             "
        ),
        @Meta.SymV(
          offset=47898,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="NumberFormatException", member="new"),
          stri="s", sig=139, nativ="new", pur=true, depth=0, rkind=33,
          doc=" " +"\n"+ "        create an 'NumberFormatException' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="NumberFormatException", member="newα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="NumberFormatException", member="newβ")
          }
        ),
        @Meta.SymV(
          offset=47898,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="NumberFormatException", member="newα"),
          stri="s(s)", sig=204, nativ="new", pur=true, depth=1, rkind=33, publik=false,
          doc=" " +"\n"+ "        create an 'NumberFormatException' value with a message ('String')" +"\n"+
          "        or with a message and a cause. The cause is another" +"\n"+ "        exception that caused this one."
          +"\n"+ "             "
        )
      },
      pur=true, nativ="java.lang.NumberFormatException", publik=false,
      doc=" @java.lang.NumberFormatException@ needed for string conversion operations   " +"\n"+
      "" +"\n"+ " declared *@protected@* to avoid name conflicts with 'frege.java.Lang#NumberFormatException'    "
    ),
    @Meta.SymT(
      offset=79521, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Maybe"), typ=205,
      kind=61,
      cons={
        @Meta.SymD(
          offset=79541, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Maybe", member="Just"), cid=1,
          typ=206, fields={@Meta.Field(offset=0, sigma=26, strict=false)}
        ),
        @Meta.SymD(
          offset=79531, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Maybe", member="Nothing"),
          cid=0, typ=205, fields={}
        )
      },
      lnks={}, funs={}
    ),
    @Meta.SymT(
      offset=47240, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="NoMatch"), typ=207,
      kind=3, cons={}, lnks={}, funs={}, pur=true, nativ="frege.runtime.NoMatch",
      doc=" A subclass of 'Undefined', used by the runtime to signal failed pattern matches   "
    ),
    @Meta.SymT(
      offset=39228, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="InterruptedException"), typ=208,
      kind=3, cons={}, lnks={}, funs={}, pur=true, nativ="java.lang.InterruptedException", publik=false
    ),
    @Meta.SymT(
      offset=12317, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer"), typ=14,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=12395, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="zero"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="zero")
        ),
        @Meta.SymL(
          offset=61037, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="succ"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="succ")
        ),
        @Meta.SymL(
          offset=14736, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="sign"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="sign")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="subtract"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="subtract")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="quotRem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="quotRem")
        ),
        @Meta.SymL(
          offset=13120, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="rem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="rem")
        ),
        @Meta.SymL(
          offset=61111, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="pred"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="pred")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="powf"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="powf")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="powg"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="powg")
        ),
        @Meta.SymL(
          offset=13033, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="quot"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="quot")
        ),
        @Meta.SymL(
          offset=12460, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="one"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="one")
        ),
        @Meta.SymL(
          offset=13444, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="negate"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="negate")
        ),
        @Meta.SymL(
          offset=62351, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="odd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="odd")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="mod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="mod")
        ),
        @Meta.SymL(
          offset=13708, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="min")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="isNumber"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isNumber")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="isInfinite"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isInfinite")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="isNaN"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="isNaN")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="lcm"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="lcm")
        ),
        @Meta.SymL(
          offset=13621, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="max")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="gcdHelper"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="gcdHelper")
        ),
        @Meta.SymL(
          offset=15028, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="hashCode")
        ),
        @Meta.SymL(
          offset=62150,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="fromIntegral"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromIntegral")
        ),
        @Meta.SymL(
          offset=62212,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="fromInteger"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromInteger")
        ),
        @Meta.SymL(
          offset=15100, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="gcd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="gcd")
        ),
        @Meta.SymL(
          offset=62308, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="even"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="even")
        ),
        @Meta.SymL(
          offset=61310, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="from"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="from")
        ),
        @Meta.SymL(
          offset=62186, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="fromInt"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="fromInt")
        ),
        @Meta.SymL(
          offset=61239, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="ord"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="ord")
        ),
        @Meta.SymL(
          offset=61660,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="enumFromThenTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromThenTo")
        ),
        @Meta.SymL(
          offset=61492,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="enumFromThen"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromThen")
        ),
        @Meta.SymL(
          offset=61445, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="enumFrom"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFrom")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="div"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="div")
        ),
        @Meta.SymL(
          offset=60627, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="compare")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="divMod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="divMod")
        ),
        @Meta.SymL(
          offset=13368, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="abs"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="abs")
        ),
        @Meta.SymL(
          offset=60771, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member=">=")
        ),
        @Meta.SymL(
          offset=62150, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="^"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="^")
        ),
        @Meta.SymL(
          offset=60845, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="==")
        ),
        @Meta.SymL(
          offset=60697, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member=">")
        ),
        @Meta.SymL(
          offset=60808, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<=")
        ),
        @Meta.SymL(
          offset=60734, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<")
        ),
        @Meta.SymL(
          offset=60660, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="<=>")
        ),
        @Meta.SymL(
          offset=12946, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="-"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="-")
        ),
        @Meta.SymL(
          offset=60882, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Integer", member="!=")
        ),
        @Meta.SymL(
          offset=12859, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="*"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="*")
        ),
        @Meta.SymL(
          offset=12772, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="+"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="+")
        ),
        @Meta.SymL(
          offset=62256, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="big"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Integer", member="big")
        ),
        @Meta.SymL(
          offset=61329, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="enumFromTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Integer", member="enumFromTo")
        )
      },
      funs={
        @Meta.SymV(
          offset=12670, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="valueOf"),
          stri="s(s)", sig=93, nativ="java.math.BigInteger.valueOf", pur=true, depth=1, rkind=33,
          doc=" construction from a 'Long', see also 'String.aton' and 'String.integer'   "
        ),
        @Meta.SymV(
          offset=14661, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="toString"),
          stri="s(s)", sig=209, nativ="toString", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=14040, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="ushiftR"),
          stri="s(ss)", sig=210, depth=2, rkind=49,
          doc=" unsigned right shift on big integers does not really make sense ...   ", op=67
        ),
        @Meta.SymV(
          offset=13891, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="shiftL"),
          stri="s(ss)", sig=210, nativ="shiftLeft", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=13808, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="shiftR"),
          stri="s(ss)", sig=210, nativ="shiftRight", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=12525, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="ten"),
          stri="s", sig=14, nativ="java.math.BigInteger.TEN", pur=true, depth=0, rkind=33
        ),
        @Meta.SymV(
          offset=13281, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="nMod"),
          stri="s(ss)", sig=77, nativ="mod", pur=true, depth=2, rkind=33,
          doc=" _Warning_! Throws @ArithmeticException@ when divisor is negative.   "
        ),
        @Meta.SymV(
          offset=14808, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="long"),
          stri="s(s)", sig=85, nativ="longValue", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=14881, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="int"),
          stri="s(s)", sig=78, nativ="intValue", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=14953, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="double"),
          stri="s(s)", sig=47, nativ="doubleValue", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=14370, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="complement"),
          stri="s(s)", sig=102, nativ="not", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=14589, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="bitLength"),
          stri="s(s)", sig=78, nativ="bitLength", pur=true, depth=1, rkind=33,
          doc=" Returns the number of bits in the minimal two's-complement representation of this 'Integer', excluding a sign bit.   "
        ),
        @Meta.SymV(
          offset=13538, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member="compareTo"),
          stri="s(ss)", sig=211, nativ="compareTo", pur=true, depth=2, rkind=33
        ),
        @Meta.SymV(
          offset=14284, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member=".^."),
          stri="s(ss)", sig=77, nativ="xor", pur=true, depth=2, rkind=33, op=65
        ),
        @Meta.SymV(
          offset=14197, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member=".&."),
          stri="s(ss)", sig=77, nativ="and", pur=true, depth=2, rkind=33, op=66
        ),
        @Meta.SymV(
          offset=14110, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integer", member=".|."),
          stri="s(ss)", sig=77, nativ="or", pur=true, depth=2, rkind=33, op=65
        )
      },
      pur=true, nativ="java.math.BigInteger",
      doc="" +"\n"+ " * 'Integer' is" +"\n"+ " * a type for integer numbers of unlimited size," +"\n"+
      " * It has instances for 'Eq', 'Ord', 'frege.prelude.PreludeText#Show' and 'Integral'." +"\n"+
      "" +"\n"+ " * This is derived from @java.math.BigInteger@." +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=7953, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="HaskellBool"), typ=212,
      kind=3,
      cons={
        @Meta.SymD(
          offset=8036, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="HaskellBool", member="True"),
          cid=1, typ=212, fields={}, doc=" will be replaced by literal @true@   "
        ),
        @Meta.SymD(
          offset=7967, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="HaskellBool", member="False"),
          cid=0, typ=212, fields={}, doc=" will be replaced by literal @false@   "
        )
      },
      lnks={}, funs={}, isEnum=true,
      doc="" +"\n"+ "    For compatibility with Haskell, the 'HaskellBool' type defines" +"\n"+
      "    constructors 'True' and 'False'." +"\n"+ "" +"\n"+
      "    When the identifiers @True@ or @False@ are used in patterns or expressions, " +"\n"+
      "    qualified or unqualified, and name resolution detects that they" +"\n"+
      "    resolve to 'PreludeBase.HaskellBool.True' or 'PreludeBase.HaskellBool.False'" +"\n"+
      "    they will be replaced by literals @true@ or @false@." +"\n"+ "" +"\n"+
      "    This is, of course, a hack, but I see no other way to bridge the gap between" +"\n"+
      "    redefinable constructor ids and literals. For example, we couldn't simply" +"\n"+
      "    define @True@ as another literal keyword, because some Haskell code " +"\n"+
      "    might use @Prelude.True@ or could have code like." +"\n"+ "    " +"\n"+
      "    > import Prelude hiding(True, False)" +"\n"+ "    > data TriBool = False | Perhaps | True " +"\n"+
      "     "
    ),
    @Meta.SymT(
      offset=35137, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float"), typ=48,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=77624, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="zero"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="zero")
        ),
        @Meta.SymL(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="sign"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="sign")
        ),
        @Meta.SymL(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="subtract"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="subtract")
        ),
        @Meta.SymL(
          offset=36455,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="negativeInfinity"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="negativeInfinity")
        ),
        @Meta.SymL(
          offset=77640, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="one"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="one")
        ),
        @Meta.SymL(
          offset=36349,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="positiveInfinity"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="positiveInfinity")
        ),
        @Meta.SymL(
          offset=36586, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="nan"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="nan")
        ),
        @Meta.SymL(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="min")
        ),
        @Meta.SymL(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="isNumber"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isNumber")
        ),
        @Meta.SymL(
          offset=77802, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="isInfinite"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isInfinite")
        ),
        @Meta.SymL(
          offset=77873, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="isNaN"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="isNaN")
        ),
        @Meta.SymL(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="max")
        ),
        @Meta.SymL(
          offset=77668, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="negate"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="negate")
        ),
        @Meta.SymL(
          offset=36246, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="hashCode")
        ),
        @Meta.SymL(
          offset=77728, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="fromInteger"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromInteger")
        ),
        @Meta.SymL(
          offset=77763, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="fromDouble"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromDouble")
        ),
        @Meta.SymL(
          offset=77701, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="fromInt"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="fromInt")
        ),
        @Meta.SymL(
          offset=77414, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="abs"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="abs")
        ),
        @Meta.SymL(
          offset=77106, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="compare")
        ),
        @Meta.SymL(
          offset=77285, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member=">")
        ),
        @Meta.SymL(
          offset=77193, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member=">=")
        ),
        @Meta.SymL(
          offset=77320, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<=>")
        ),
        @Meta.SymL(
          offset=77147, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<=")
        ),
        @Meta.SymL(
          offset=77029, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="==")
        ),
        @Meta.SymL(
          offset=77591, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="/"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="/")
        ),
        @Meta.SymL(
          offset=77501, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="-"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="-")
        ),
        @Meta.SymL(
          offset=77546, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="*"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="*")
        ),
        @Meta.SymL(
          offset=77075, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Float", member="!=")
        ),
        @Meta.SymL(
          offset=77456, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="+"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Float", member="+")
        ),
        @Meta.SymL(
          offset=77239, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Float", member="<")
        )
      },
      funs={
        @Meta.SymV(
          offset=35974, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="int"),
          stri="s(s)", sig=49, nativ="java.lang.Math.round", pur=true, depth=1, rkind=33,
          doc="" +"\n"+ "      Returns the closest 'Int' value to the argument." +"\n"+
          "      The result is rounded to an integer by adding 1/2," +"\n"+
          "      taking the 'frege.prelude.Math#floor' of the result," +"\n"+
          "      and casting the result to type int." +"\n"+ "" +"\n"+ "      The following property holds:" +"\n"+
          "" +"\n"+ "      > (f < Int.maxBound.float && f > Int.minBound.float) ==>" +"\n"+
          "      >   (f.int.float == (f + 0.5f).floor)" +"\n"+ "" +"\n"+ "      Special cases:" +"\n"+
          "      - If the argument is NaN, the result is 0." +"\n"+
          "      - If the argument is negative infinity or any value less than or equal" +"\n"+
          "      to the value of 'Int.minBound', the result is equal to the value of" +"\n"+
          "      'Int.minBound'." +"\n"+
          "      - If the argument is positive infinity or any value greater than or equal" +"\n"+
          "      to the value of 'Int.maxBound', the result is equal to the value of" +"\n"+
          "      'Int.maxBound'." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=36121, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Float", member="double"),
          stri="s(s)", sig=213, nativ="(double)", pur=true, depth=1, rkind=33,
          doc=" Applies the java widening primitive conversion from @float@ to @double@.   "
        )
      },
      pur=true, nativ="float",
      doc=" 'Float' values are based on Java's primitive @float@ values.   " +"\n"+ ""
      +"\n"+ "" +"\n"+ "    According to the Java Language Specification §4.2.3, @float@ values are" +"\n"+
      "    32-bit-precision binary floating point values. The values and the operations" +"\n"+
      "    on it behave as specified in the IEEE Standard for Binary Floating-Point Arithmetic." +"\n"+
      "     "
    ),
    @Meta.SymT(
      offset=47369, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="GuardFailed"), typ=214,
      kind=3, cons={}, lnks={}, funs={}, pur=true, nativ="frege.runtime.GuardFailed",
      doc=" A subclass of 'Undefined', used by the runtime to signal failed guards   "
    ),
    @Meta.SymT(
      offset=9922, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int"), typ=16,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=67191, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="zero"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="zero")
        ),
        @Meta.SymL(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="subtract"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="subtract")
        ),
        @Meta.SymL(
          offset=70902, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="succ"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="succ")
        ),
        @Meta.SymL(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="sign"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="sign")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="quotRem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="quotRem")
        ),
        @Meta.SymL(
          offset=73314, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="quot"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="quot")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="powg"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="powg")
        ),
        @Meta.SymL(
          offset=70562, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="ord"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="ord")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="powf"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="powf")
        ),
        @Meta.SymL(
          offset=70718, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="pred"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="pred")
        ),
        @Meta.SymL(
          offset=73267, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="rem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="rem")
        ),
        @Meta.SymL(
          offset=73389, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="odd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="odd")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="mod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="mod")
        ),
        @Meta.SymL(
          offset=67856, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="negate"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="negate")
        ),
        @Meta.SymL(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="min")
        ),
        @Meta.SymL(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="max")
        ),
        @Meta.SymL(
          offset=70487, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="maxBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Int", member="maxBound")
        ),
        @Meta.SymL(
          offset=70401, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="minBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Int", member="minBound")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="lcm"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="lcm")
        ),
        @Meta.SymL(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="isNumber"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isNumber")
        ),
        @Meta.SymL(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="isInfinite"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isInfinite")
        ),
        @Meta.SymL(
          offset=66814, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="isNaN"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="isNaN")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="gcdHelper"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="gcdHelper")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="fromIntegral"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="fromIntegral")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="gcd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="gcd")
        ),
        @Meta.SymL(
          offset=70605, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="from"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="from")
        ),
        @Meta.SymL(
          offset=67942, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="fromInt"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="fromInt")
        ),
        @Meta.SymL(
          offset=67960, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="fromInteger"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="fromInteger")
        ),
        @Meta.SymL(
          offset=12086, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="hashCode")
        ),
        @Meta.SymL(
          offset=67235, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="one"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="one")
        ),
        @Meta.SymL(
          offset=73350, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="even"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="even")
        ),
        @Meta.SymL(
          offset=71236, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="enumFromThenTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromThenTo")
        ),
        @Meta.SymL(
          offset=70994, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="enumFromTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromTo")
        ),
        @Meta.SymL(
          offset=71110, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="enumFrom"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFrom")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="div"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="div")
        ),
        @Meta.SymL(
          offset=66242, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="compare")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="divMod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="divMod")
        ),
        @Meta.SymL(
          offset=71155, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="enumFromThen"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Int", member="enumFromThen")
        ),
        @Meta.SymL(
          offset=73429, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="big"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="big")
        ),
        @Meta.SymL(
          offset=73223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="^"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Int", member="^")
        ),
        @Meta.SymL(
          offset=66462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member=">=")
        ),
        @Meta.SymL(
          offset=67530, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="abs"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="abs")
        ),
        @Meta.SymL(
          offset=66720, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<=>")
        ),
        @Meta.SymL(
          offset=66098, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="==")
        ),
        @Meta.SymL(
          offset=66686, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member=">")
        ),
        @Meta.SymL(
          offset=66574, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<")
        ),
        @Meta.SymL(
          offset=67132, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="-"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="-")
        ),
        @Meta.SymL(
          offset=67015, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="*"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="*")
        ),
        @Meta.SymL(
          offset=66211, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Int", member="!=")
        ),
        @Meta.SymL(
          offset=66911, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="+"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="+")
        ),
        @Meta.SymL(
          offset=66349, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<=")
        )
      },
      funs={
        @Meta.SymV(
          offset=11244, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="ushiftR"),
          stri="s(ss)", sig=63, nativ=">>>", pur=true, depth=2, rkind=33, doc=" unsigned right shift   ", op=67
        ),
        @Meta.SymV(
          offset=11166, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="shiftR"),
          stri="s(ss)", sig=63, nativ=">>", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=11065, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="size"), stri="s",
          sig=16, nativ="java.lang.Integer.SIZE", pur=true, depth=0, rkind=33,
          doc=" The number of bits used to represent an int value in two's complement binary form   "
        ),
        @Meta.SymV(
          offset=11972, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="toHexString"),
          stri="s(s)", sig=151, nativ="java.lang.Integer.toHexString", pur=true, depth=1,
          rkind=33, doc=" convert to a hexadecimal string   "
        ),
        @Meta.SymV(
          offset=11743, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="rotateR"),
          stri="s(ss)", sig=63, nativ="java.lang.Integer.rotateRight", pur=true, depth=2,
          rkind=33,
          doc=" Returns the value obtained by rotating the two's complement binary representation of the specified int value   "
          +"\n"+ "" +"\n"+ " right by the specified number of bits.   ",
          op=67
        ),
        @Meta.SymV(
          offset=11504, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="rotateL"),
          stri="s(ss)", sig=63, nativ="java.lang.Integer.rotateLeft", pur=true, depth=2,
          rkind=33,
          doc=" Returns the value obtained by rotating the two's complement binary representation of the specified int value left   "
          +"\n"+ "" +"\n"+ " by the specified number of bits.   ",
          op=67
        ),
        @Meta.SymV(
          offset=11117, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="shiftL"),
          stri="s(ss)", sig=63, nativ="<<", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=10343, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="long"),
          stri="s(s)", sig=84, nativ="java.lang.Long.valueOf", pur=true, depth=1, rkind=33,
          doc=" Convert an 'Int' to a 'Long', i.e. @2.long == 2L@.   "
        ),
        @Meta.SymV(
          offset=10218, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="double"),
          stri="s(s)", sig=46, nativ="java.lang.Double.valueOf", pur=true, depth=1, rkind=33,
          doc=" convert an 'Int' to a 'Double', i.e. @2.double == 2.0@.   "
        ),
        @Meta.SymV(
          offset=11295, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="complement"),
          stri="s(s)", sig=86, nativ="~", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=10541, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="char"),
          stri="s(s)", sig=122, nativ="(char)", pur=true, depth=1, rkind=33,
          doc=" @i.char@ returns the 'Char' value whose ordinal number is @i@   " +"\n"+
          "" +"\n"+ " Result is only valid for integers in the range 0..65535   "
        ),
        @Meta.SymV(
          offset=10673, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member=".|."),
          stri="s(ss)", sig=63, nativ="|", pur=true, depth=2, rkind=33,
          doc=" Computes binary /or/ of two integers. Uses the java @|@-operator.   ", op=65
        ),
        @Meta.SymV(
          offset=10928, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member=".&."),
          stri="s(ss)", sig=63, nativ="&", pur=true, depth=2, rkind=33,
          doc=" Computes binary /and/ of two integers. Uses the java @&@-operator.   ", op=66
        ),
        @Meta.SymV(
          offset=10805, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member=".^."),
          stri="s(ss)", sig=63, nativ="^", pur=true, depth=2, rkind=33,
          doc=" Computes binary /exclusive or/ of two integers. Uses the java @^@-operator.   ", op=65
        ),
        @Meta.SymV(
          offset=10089, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Int", member="float"),
          stri="s(s)", sig=53, nativ="java.lang.Float.valueOf", pur=true, depth=1, rkind=33,
          doc=" convert an 'Int' to a 'Float', i.e. @2.float == 2.0f@.   " +"\n"+ ""
          +"\n"+ " For large integers, the result may have been be rounded.   "
        )
      },
      pur=true, nativ="int",
      doc="" +"\n"+ "    'Int' values are based on Java's primitive @int@ values." +"\n"+
      "" +"\n"+ "    The existence of this type is assumed in numerous places in the compiler." +"\n"+
      "" +"\n"+ "    Like with all @native@ Java types, be they primitive or reference types," +"\n"+
      "    Frege holds the raw @int@ in boxed form. However, in certain cases the" +"\n"+
      "    compiler will optimize the boxing away:" +"\n"+
      "    - Strict variables or function arguments work with the unboxed value directly." +"\n"+
      "    - Functions with a @native@ return type generally return the unboxed value." +"\n"+
      "    Polymorphic data structures or functions always work with boxed values." +"\n"+
      "    Thus, for example, the function" +"\n"+ "    > sum a b c = a + b + c" +"\n"+
      "    can compute the sum of 3 'Int's, 'Long's, 'Double's or any other values" +"\n"+
      "    of a type that is an instance of type class 'Num', but it may be somewhat" +"\n"+
      "    slower than functions specialized for a given type." +"\n"+ "" +"\n"+
      "    According to the Java Language Specification, @int@ values are" +"\n"+
      "    32 bit wide signed two's complement integers (§4.2). Java operations on @int@ do" +"\n"+
      "    not indicate overflow or underflow in any way (§4.2.2). Instead, just the low 32 bits of" +"\n"+
      "    every result are retained." +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=36958, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double"), typ=6,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=78659, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="zero"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="zero")
        ),
        @Meta.SymL(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="sign"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="sign")
        ),
        @Meta.SymL(
          offset=38297,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="positiveInfinity"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="positiveInfinity")
        ),
        @Meta.SymL(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="subtract"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="subtract")
        ),
        @Meta.SymL(
          offset=38405,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="negativeInfinity"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="negativeInfinity")
        ),
        @Meta.SymL(
          offset=78700, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="negate"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="negate")
        ),
        @Meta.SymL(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="min")
        ),
        @Meta.SymL(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="max")
        ),
        @Meta.SymL(
          offset=38538, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="nan"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="nan")
        ),
        @Meta.SymL(
          offset=78674, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="one"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="one")
        ),
        @Meta.SymL(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="isNumber"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isNumber")
        ),
        @Meta.SymL(
          offset=78898, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="isNaN"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isNaN")
        ),
        @Meta.SymL(
          offset=38219, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="hashCode")
        ),
        @Meta.SymL(
          offset=78825, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="isInfinite"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="isInfinite")
        ),
        @Meta.SymL(
          offset=78792, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="fromDouble"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromDouble")
        ),
        @Meta.SymL(
          offset=78735, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="fromInt"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromInt")
        ),
        @Meta.SymL(
          offset=78436, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="abs"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="abs")
        ),
        @Meta.SymL(
          offset=78115, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="compare")
        ),
        @Meta.SymL(
          offset=78301, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member=">")
        ),
        @Meta.SymL(
          offset=78205, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member=">=")
        ),
        @Meta.SymL(
          offset=78338, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<=>")
        ),
        @Meta.SymL(
          offset=78157, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<=")
        ),
        @Meta.SymL(
          offset=78623, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="/"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="/")
        ),
        @Meta.SymL(
          offset=78527, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="-"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="-")
        ),
        @Meta.SymL(
          offset=78575, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="*"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="*")
        ),
        @Meta.SymL(
          offset=78081, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="!=")
        ),
        @Meta.SymL(
          offset=78479, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="+"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="+")
        ),
        @Meta.SymL(
          offset=78253, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Double", member="<")
        ),
        @Meta.SymL(
          offset=78033, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Double", member="==")
        ),
        @Meta.SymL(
          offset=78763, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="fromInteger"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real_Double", member="fromInteger")
        )
      },
      funs={
        @Meta.SymV(
          offset=38072, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="longBits"),
          stri="s(s)", sig=215, nativ="java.lang.Double.doubleToLongBits", pur=true, depth=1,
          rkind=33, doc=" bit representation of a double   "
        ),
        @Meta.SymV(
          offset=37812, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="long"),
          stri="s(s)", sig=215, nativ="java.lang.Math.round", pur=true, depth=1, rkind=33,
          doc="" +"\n"+ "      Returns the closest 'Long' value to the argument." +"\n"+
          "      The result is rounded to an integer by adding 1/2," +"\n"+
          "      taking the 'frege.prelude.Math#floor' of the result," +"\n"+
          "      and casting the result to type @long@." +"\n"+ "" +"\n"+ "      The following property holds:" +"\n"+
          "" +"\n"+ "      > (d < Long.maxBound.double && d > Long.minBound.double) ==>" +"\n"+
          "      >   (d.long.double == (d + 0.5d).floor)" +"\n"+ "" +"\n"+ "      Special cases:" +"\n"+
          "      - If the argument is NaN, the result is 0." +"\n"+
          "      - If the argument is negative infinity or any value less than or equal" +"\n"+
          "      to the value of 'Long.minBound', the result is equal to the value of" +"\n"+
          "      'Long.minBound'." +"\n"+
          "      - If the argument is positive infinity or any value greater than or equal" +"\n"+
          "      to the value of 'Long.maxBound', the result is equal to the value of" +"\n"+
          "      'Long.maxBound'." +"\n"+ "          "
        ),
        @Meta.SymV(
          offset=37965, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Double", member="float"),
          stri="s(s)", sig=54, nativ="(float)", pur=true, depth=1, rkind=33,
          doc=" Applies the java narrowing primitive conversion from @double@ to @float@   "
        )
      },
      pur=true, nativ="double",
      doc=" 'Double' values are Java's primitive @double@ values.   " +"\n"+ "" +"\n"+
      "" +"\n"+ "    According to the Java Language Specification §4.2.3, @double@ values are" +"\n"+
      "    64-bit-precision binary floating point values. The values and the operations" +"\n"+
      "    on it behave as specified in the IEEE Standard for Binary Floating-Point Arithmetic." +"\n"+
      "         "
    ),
    @Meta.SymT(
      offset=39780, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Class"), typ=216,
      kind=61, cons={}, lnks={},
      funs={
        @Meta.SymV(
          offset=39890, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Class", member="forName"),
          stri="s(s)", sig=217, nativ="java.lang.Class.forName", depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=39841, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Class", member="getName"),
          stri="s(s)", sig=219, nativ="getName", pur=true, depth=1, rkind=33
        )
      },
      pur=true, nativ="java.lang.Class", gargs={22},
      doc="" +"\n"+ " * We need to do some reflection from frege code." +"\n"+
      " * For example, when we catch a 'Throwable' thrown from Java code." +"\n"+ " * we might want to know what it is."
      +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=48090, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="ClassNotFoundException"), typ=220,
      kind=3, cons={}, lnks={}, funs={}, pur=true, nativ="java.lang.ClassNotFoundException", publik=false,
      doc=" Forward declaration of 'frege.java.Lang#ClassNotFoundException'   "
    ),
    @Meta.SymT(
      offset=79873, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Either"), typ=221,
      kind=76,
      cons={
        @Meta.SymD(
          offset=79895, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Either", member="Right"),
          cid=1, typ=222, fields={@Meta.Field(offset=0, sigma=156, strict=false)}
        ),
        @Meta.SymD(
          offset=79886, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Either", member="Left"), cid=0,
          typ=223, fields={@Meta.Field(offset=0, sigma=26, strict=false)}
        )
      },
      lnks={}, funs={}
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,,)"), typ=224,
      kind=153,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,,)",
            member="(,,,,,,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=238,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false), @Meta.Field(offset=0, sigma=233, strict=false),
            @Meta.Field(offset=0, sigma=234, strict=false), @Meta.Field(offset=0, sigma=235, strict=false),
            @Meta.Field(offset=0, sigma=236, strict=false), @Meta.Field(offset=0, sigma=237, strict=false)
          },
          doc="26-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="26-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="->"), typ=239,
      kind=76, cons={}, lnks={}, funs={}, doc="function"
    ),
    @Meta.SymT(
      offset=7148, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool"), typ=70,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=74361, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="succ"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="succ")
        ),
        @Meta.SymL(
          offset=74304, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="pred"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="pred")
        ),
        @Meta.SymL(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="min")
        ),
        @Meta.SymL(
          offset=74125, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="minBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Bool", member="minBound")
        ),
        @Meta.SymL(
          offset=74196, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="ord"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="ord")
        ),
        @Meta.SymL(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="max")
        ),
        @Meta.SymL(
          offset=74232, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="from"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="from")
        ),
        @Meta.SymL(
          offset=5979, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="hashCode")
        ),
        @Meta.SymL(
          offset=74148, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="maxBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Bool", member="maxBound")
        ),
        @Meta.SymL(
          offset=74585,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="enumFromThenTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromThenTo")
        ),
        @Meta.SymL(
          offset=74510, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="enumFromThen"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromThen")
        ),
        @Meta.SymL(
          offset=73779, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="compare")
        ),
        @Meta.SymL(
          offset=73868, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member=">")
        ),
        @Meta.SymL(
          offset=73974, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member=">=")
        ),
        @Meta.SymL(
          offset=74630, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="enumFrom"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFrom")
        ),
        @Meta.SymL(
          offset=74026, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<=>")
        ),
        @Meta.SymL(
          offset=73923, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<=")
        ),
        @Meta.SymL(
          offset=5948, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="!=")
        ),
        @Meta.SymL(
          offset=73813, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Bool", member="<")
        ),
        @Meta.SymL(
          offset=5905, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="==")
        ),
        @Meta.SymL(
          offset=74418, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bool", member="enumFromTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Bool", member="enumFromTo")
        )
      },
      funs={}, pur=true, nativ="boolean",
      doc="" +"\n"+ "    'Bool' values are based on Java's primitive @boolean@ values." +"\n"+
      "    Note that @true@ and @false@ are literals, not constructors." +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=17668, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char"), typ=73,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=76022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="succ"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="succ")
        ),
        @Meta.SymL(
          offset=17906, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="ord"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="ord")
        ),
        @Meta.SymL(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="min")
        ),
        @Meta.SymL(
          offset=75787, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="minBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Char", member="minBound")
        ),
        @Meta.SymL(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="max")
        ),
        @Meta.SymL(
          offset=75852, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="maxBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Char", member="maxBound")
        ),
        @Meta.SymL(
          offset=75982, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="pred"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="pred")
        ),
        @Meta.SymL(
          offset=17989, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="hashCode")
        ),
        @Meta.SymL(
          offset=75943, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="from"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="from")
        ),
        @Meta.SymL(
          offset=76062, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="enumFromTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromTo")
        ),
        @Meta.SymL(
          offset=76223, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="enumFromThen"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromThen")
        ),
        @Meta.SymL(
          offset=76304,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="enumFromThenTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFromThenTo")
        ),
        @Meta.SymL(
          offset=75467, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="compare")
        ),
        @Meta.SymL(
          offset=75713, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member=">")
        ),
        @Meta.SymL(
          offset=75625, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member=">=")
        ),
        @Meta.SymL(
          offset=76178, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="enumFrom"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="enumFrom")
        ),
        @Meta.SymL(
          offset=75496, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<=>")
        ),
        @Meta.SymL(
          offset=75669, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<")
        ),
        @Meta.SymL(
          offset=75438, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="!=")
        ),
        @Meta.SymL(
          offset=75581, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Char", member="<=")
        ),
        @Meta.SymL(
          offset=75395, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Char", member="==")
        )
      },
      funs={
        @Meta.SymV(
          offset=18762, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toUpperCaseβ"),
          stri="s(s)", sig=86, nativ="java.lang.Character.toUpperCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The uppercase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=18762, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toUpperCase"),
          stri="s", sig=139, nativ="java.lang.Character.toUpperCase", pur=true, depth=0,
          rkind=33, doc=" The uppercase equivalent of the character, if any; otherwise, the character itself.   ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toUpperCaseα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toUpperCaseβ")
          }
        ),
        @Meta.SymV(
          offset=19022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toTitleCaseα"),
          stri="s(s)", sig=121, nativ="java.lang.Character.toTitleCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The titlecase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=19022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toTitleCaseβ"),
          stri="s(s)", sig=86, nativ="java.lang.Character.toTitleCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The titlecase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=18762, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toUpperCaseα"),
          stri="s(s)", sig=121, nativ="java.lang.Character.toUpperCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The uppercase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=18333, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toLowerCaseβ"),
          stri="s(s)", sig=86, nativ="java.lang.Character.toLowerCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The lowercase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=18333, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toLowerCase"),
          stri="s", sig=139, nativ="java.lang.Character.toLowerCase", pur=true, depth=0,
          rkind=33, doc=" The lowercase equivalent of the character, if any; otherwise, the character itself.   ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toLowerCaseα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toLowerCaseβ")
          }
        ),
        @Meta.SymV(
          offset=18333, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toLowerCaseα"),
          stri="s(s)", sig=121, nativ="java.lang.Character.toLowerCase", pur=true, depth=1,
          rkind=33, publik=false,
          doc=" The lowercase equivalent of the character, if any; otherwise, the character itself.   "
        ),
        @Meta.SymV(
          offset=25258, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toCodePoint"),
          stri="s(ss)", sig=240, nativ="java.lang.Character.toCodePoint", pur=true, depth=2,
          rkind=33,
          doc="" +"\n"+ "        > Char.toCodePoint high low" +"\n"+ "        " +"\n"+
          "        Converts the specified surrogate pair to its supplementary code point value. " +"\n"+
          "        This function does not validate the specified surrogate pair. " +"\n"+
          "        The caller must validate it using 'Char.isSurrogatePair' if necessary." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=19022, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toTitleCase"),
          stri="s", sig=139, nativ="java.lang.Character.toTitleCase", pur=true, depth=0,
          rkind=33, doc=" The titlecase equivalent of the character, if any; otherwise, the character itself.   ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toTitleCaseα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="toTitleCaseβ")
          }
        ),
        @Meta.SymV(
          offset=24877, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="lowSurrogate"),
          stri="s(s)", sig=122, nativ="java.lang.Character.lowSurrogate", pur=true, depth=1,
          rkind=33,
          doc="" +"\n"+ "        The trailing surrogate (which is a low surrogate code unit) of the" +"\n"+
          "        surrogate pair representing the specified supplementary character" +"\n"+
          "        (Unicode code point) in the UTF-16 encoding. " +"\n"+ "        " +"\n"+
          "        If the specified value is not a supplementary character, " +"\n"+
          "        an unspecified character is returned." +"\n"+ "        " +"\n"+
          "        See also: 'Char.isSupplementaryCodePoint', 'Char.highSurrogate', 'Char.toCodePoint'" +"\n"+
          "        " +"\n"+ "        The following holds:" +"\n"+ "        " +"\n"+
          "        > isSupplementaryCodePoint x ==> (x == toCodePoint (highSurrogate x) (lowSurrogate x))" +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=19189, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isWhitespaceα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isWhitespace", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=23025,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isValidCodePoint"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isValidCodePoint", pur=true, depth=1,
          rkind=33,
          doc=" Determines whether the specified code point is a valid Unicode code point value.   " +"\n"+
          "" +"\n"+ " This is the case if it is not negative and lower than @0x110000@   "
        ),
        @Meta.SymV(
          offset=18501, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isUpperCaseα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isUpperCase", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=18501, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isUpperCaseβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isUpperCase", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=19189, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isWhitespace"),
          stri="s", sig=139, nativ="java.lang.Character.isWhitespace", pur=true, depth=0,
          rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isWhitespaceα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isWhitespaceβ")
          }
        ),
        @Meta.SymV(
          offset=19189, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isWhitespaceβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isWhitespace", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=25458,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isSurrogatePair"),
          stri="s(ss)", sig=76, nativ="java.lang.Character.isSurrogatePair", pur=true, depth=2,
          rkind=33,
          doc="" +"\n"+
          "        Determines whether the specified pair of char values is a valid Unicode surrogate pair." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=22730,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isSupplementaryCodePoint"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isSupplementaryCodePoint", pur=true,
          depth=1, rkind=33,
          doc="" +"\n"+ "        Determines whether the specified character (Unicode code point) " +"\n"+
          "        is in the supplementary character range." +"\n"+ "        " +"\n"+
          "        This is roughly equivalent to" +"\n"+ "        " +"\n"+ "        > (> ord Char.maxBound)" +"\n"+
          "        " +"\n"+ "        except that it also checks if the value is lower than @0x110000@" +"\n"+
          "        " +"\n"+ "        If, for some integer @n@" +"\n"+ "        " +"\n"+
          "        > not (Char.isSupplementaryCodePoint n) && (n > ord Char.maxBound)" +"\n"+
          "        " +"\n"+ "        then @n@ is not a codepoint at all. " +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=18072, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowerCaseα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isLowerCase", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=18072, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowerCaseβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isLowerCase", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=21370, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isSurrogate"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isSurrogate", pur=true, depth=1,
          rkind=33,
          doc="" +"\n"+ "        Determines if the given char value is a Unicode surrogate code unit." +"\n"+
          "" +"\n"+ "        Such values do not represent characters by themselves, " +"\n"+
          "        but are used in the representation of supplementary characters " +"\n"+
          "        in the UTF-16 encoding." +"\n"+ "" +"\n"+
          "        A char value is a surrogate code unit if and only if " +"\n"+
          "        it is either a low-surrogate code unit or a high-surrogate code unit." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=18501, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isUpperCase"),
          stri="s", sig=139, nativ="java.lang.Character.isUpperCase", pur=true, depth=0,
          rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isUpperCaseα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isUpperCaseβ")
          }
        ),
        @Meta.SymV(
          offset=20363, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isLetter", pur=true, depth=1, rkind=33,
          publik=false
        ),
        @Meta.SymV(
          offset=21763,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowSurrogate"), stri="s(s)",
          sig=241, nativ="java.lang.Character.isLowSurrogate", pur=true, depth=1, rkind=33,
          doc="" +"\n"+ "        Determines if the given char value is a Unicode low-surrogate code unit " +"\n"+
          "        (also known as trailing-surrogate code unit)." +"\n"+ "" +"\n"+
          "        Such values do not represent characters by themselves, " +"\n"+
          "        but are used in the representation of supplementary characters in the UTF-16 encoding." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=19357,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterOrDigitβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isLetterOrDigit", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=19357,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterOrDigit"), stri="s",
          sig=139, nativ="java.lang.Character.isLetterOrDigit", pur=true, depth=0, rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterOrDigitα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterOrDigitβ")
          }
        ),
        @Meta.SymV(
          offset=19357,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterOrDigitα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isLetterOrDigit", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=20363, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isLetter", pur=true, depth=1,
          rkind=33, publik=false
        ),
        @Meta.SymV(
          offset=20802, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isISOControlβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isISOControl", pur=true, depth=1,
          rkind=33, publik=false,
          doc="" +"\n"+ "        Determines if the specified character is an ISO control character. " +"\n"+
          "        A character is considered to be an ISO control character if its code is " +"\n"+
          "        in the range \"\\u0000\" through \"\\u001F\" " +"\n"+
          "        or in the range \"\\u007F\" through \"\\u009F\"." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=20802, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isISOControl"),
          stri="s", sig=139, nativ="java.lang.Character.isISOControl", pur=true, depth=0,
          rkind=33,
          doc="" +"\n"+ "        Determines if the specified character is an ISO control character. " +"\n"+
          "        A character is considered to be an ISO control character if its code is " +"\n"+
          "        in the range \"\\u0000\" through \"\\u001F\" " +"\n"+
          "        or in the range \"\\u007F\" through \"\\u009F\"." +"\n"+ "         ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isISOControlα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isISOControlβ")
          }
        ),
        @Meta.SymV(
          offset=20802, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isISOControlα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isISOControl", pur=true, depth=1,
          rkind=33, publik=false,
          doc="" +"\n"+ "        Determines if the specified character is an ISO control character. " +"\n"+
          "        A character is considered to be an ISO control character if its code is " +"\n"+
          "        in the range \"\\u0000\" through \"\\u001F\" " +"\n"+
          "        or in the range \"\\u007F\" through \"\\u009F\"." +"\n"+ "         "
        ),
        @Meta.SymV(
          offset=19525, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDigitβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isDigit", pur=true, depth=1, rkind=33,
          publik=false
        ),
        @Meta.SymV(
          offset=19525, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDigitα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isDigit", pur=true, depth=1, rkind=33,
          publik=false
        ),
        @Meta.SymV(
          offset=22156,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isHighSurrogate"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isHighSurrogate", pur=true, depth=1,
          rkind=33,
          doc="" +"\n"+ "        Determines if the given char value is a Unicode high-surrogate code unit " +"\n"+
          "        (also known as leading-surrogate code unit)." +"\n"+ "" +"\n"+
          "        Such values do not represent characters by themselves, " +"\n"+
          "        but are used in the representation of supplementary characters in the UTF-16 encoding." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=23437, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDefinedβ"),
          stri="s(s)", sig=87, nativ="java.lang.Character.isDefined", pur=true, depth=1,
          rkind=33, publik=false,
          doc="" +"\n"+ "        Determines if a character (Unicode code point) is defined in Unicode." +"\n"+
          "        " +"\n"+ "        A character is defined if at least one of the following is true:" +"\n"+
          "" +"\n"+ "        - It has an entry in the UnicodeData file." +"\n"+
          "        - It has a value in a range defined by the UnicodeData file." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=23437, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDefined"),
          stri="s", sig=139, nativ="java.lang.Character.isDefined", pur=true, depth=0, rkind=33,
          doc="" +"\n"+ "        Determines if a character (Unicode code point) is defined in Unicode." +"\n"+
          "        " +"\n"+ "        A character is defined if at least one of the following is true:" +"\n"+
          "" +"\n"+ "        - It has an entry in the UnicodeData file." +"\n"+
          "        - It has a value in a range defined by the UnicodeData file." +"\n"+
          "         ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDefinedα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDefinedβ")
          }
        ),
        @Meta.SymV(
          offset=23437, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDefinedα"),
          stri="s(s)", sig=241, nativ="java.lang.Character.isDefined", pur=true, depth=1,
          rkind=33, publik=false,
          doc="" +"\n"+ "        Determines if a character (Unicode code point) is defined in Unicode." +"\n"+
          "        " +"\n"+ "        A character is defined if at least one of the following is true:" +"\n"+
          "" +"\n"+ "        - It has an entry in the UnicodeData file." +"\n"+
          "        - It has a value in a range defined by the UnicodeData file." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=19525, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDigit"),
          stri="s", sig=139, nativ="java.lang.Character.isDigit", pur=true, depth=0, rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDigitα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isDigitβ")
          }
        ),
        @Meta.SymV(
          offset=20363, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetter"),
          stri="s", sig=139, nativ="java.lang.Character.isLetter", pur=true, depth=0, rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLetterβ")
          }
        ),
        @Meta.SymV(
          offset=25637, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getTypeα"),
          stri="s(s)", sig=114, nativ="java.lang.Character.getType", pur=true, depth=1, rkind=33,
          publik=false,
          doc="" +"\n"+ "        Returns a value indicating a character's general category." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=25637, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getTypeβ"),
          stri="s(s)", sig=86, nativ="java.lang.Character.getType", pur=true, depth=1, rkind=33,
          publik=false,
          doc="" +"\n"+ "        Returns a value indicating a character's general category." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=25895, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getNameβ"),
          stri="s(s)", sig=242, nativ="java.lang.Character.getName", pur=true, depth=1, rkind=33,
          publik=false,
          doc="" +"\n"+ "        Return the Unicode name of the code point or null if it is unassigned." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=25895, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getNameα"),
          stri="s(s)", sig=243, nativ="java.lang.Character.getName", pur=true, depth=1, rkind=33,
          publik=false,
          doc="" +"\n"+ "        Return the Unicode name of the code point or null if it is unassigned." +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=25895, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getName"),
          stri="s", sig=139, nativ="java.lang.Character.getName", pur=true, depth=0, rkind=33,
          doc="" +"\n"+ "        Return the Unicode name of the code point or null if it is unassigned." +"\n"+
          "         ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getNameα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getNameβ")
          }
        ),
        @Meta.SymV(
          offset=25637, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getType"),
          stri="s", sig=139, nativ="java.lang.Character.getType", pur=true, depth=0, rkind=33,
          doc="" +"\n"+ "        Returns a value indicating a character's general category." +"\n"+
          "         ",
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getTypeα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="getTypeβ")
          }
        ),
        @Meta.SymV(
          offset=19930, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="digit"),
          stri="s(ss)", sig=244, nativ="java.lang.Character.digit", pur=true, depth=2, rkind=33,
          doc=" Returns the numeric value of the character argument in the specified radix.   " +"\n"+
          "" +"\n"+ " If the character is not a digit in the given radix, -1 is returned.   " +"\n"+
          "" +"\n"+ " The radix must be in the range 2..36, otherwise also -1 is returned.   "
        ),
        @Meta.SymV(
          offset=20273, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="forDigit"),
          stri="s(ss)", sig=245, nativ="java.lang.Character.forDigit", pur=true, depth=2,
          rkind=33,
          doc=" > Char.forDigit d r   " +"\n"+ "" +"\n"+ " returns the character for the digit _d_ in radix _r_   "
          +"\n"+ "" +"\n"+ " _d_ must not be negative and lower than _r_, and _r_ must be in the range 2..36   " +"\n"+
          "" +"\n"+ " When the arguments are invalid the character \\'\\u0000\\' is returned.   "
        ),
        @Meta.SymV(
          offset=24212, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="highSurrogate"),
          stri="s(s)", sig=122, nativ="java.lang.Character.highSurrogate", pur=true, depth=1,
          rkind=33,
          doc="" +"\n"+ "        The leading surrogate (which is a high surrogate code unit) of the" +"\n"+
          "        surrogate pair representing the specified supplementary character" +"\n"+
          "        (Unicode code point) in the UTF-16 encoding. " +"\n"+ "        " +"\n"+
          "        If the specified value is not a supplementary character, " +"\n"+
          "        an unspecified character is returned." +"\n"+ "        " +"\n"+
          "        See also: 'Char.isSupplementaryCodePoint', 'Char.lowSurrogate', 'Char.toCodePoint'" +"\n"+
          "        " +"\n"+ "        The following holds:" +"\n"+ "        " +"\n"+
          "        > isSupplementaryCodePoint x ==> (x == toCodePoint (highSurrogate x) (lowSurrogate x))" +"\n"+
          "         "
        ),
        @Meta.SymV(
          offset=18072, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowerCase"),
          stri="s", sig=139, nativ="java.lang.Character.isLowerCase", pur=true, depth=0,
          rkind=33,
          over={
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowerCaseα"),
            @Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Char", member="isLowerCaseβ")
          }
        )
      },
      pur=true, nativ="char",
      doc=" 'Char' values are based on Java's primitive @char@ values.   " +"\n"+ ""
      +"\n"+ " This type has many native functions based on the methods in @java.lang.Character@.    " +"\n"+
      "" +"\n"+ " Most @is...@ functions work on 'Char'  and 'Int' (codepoint).   " +"\n"+
      "" +"\n"+ " Likewise, most @to...Case@ functions work on codepoints.   "
    ),
    @Meta.SymT(
      offset=15524, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long"), typ=56,
      kind=3, cons={},
      lnks={
        @Meta.SymL(
          offset=69216, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="zero"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="zero")
        ),
        @Meta.SymL(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="subtract"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="subtract")
        ),
        @Meta.SymL(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="sign"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="sign")
        ),
        @Meta.SymL(
          offset=72446, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="succ"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="succ")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="quotRem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="quotRem")
        ),
        @Meta.SymL(
          offset=73558, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="quot"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="quot")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="powg"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="powg")
        ),
        @Meta.SymL(
          offset=72030, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="ord"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="ord")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="powf"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="powf")
        ),
        @Meta.SymL(
          offset=72257, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="pred"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="pred")
        ),
        @Meta.SymL(
          offset=73507, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="rem"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="rem")
        ),
        @Meta.SymL(
          offset=73637, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="odd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="odd")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="mod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="mod")
        ),
        @Meta.SymL(
          offset=69883, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="negate"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="negate")
        ),
        @Meta.SymL(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="min"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="min")
        ),
        @Meta.SymL(
          offset=71783, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="minBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Long", member="minBound")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="lcm"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="lcm")
        ),
        @Meta.SymL(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="max"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="max")
        ),
        @Meta.SymL(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="isInfinite"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isInfinite")
        ),
        @Meta.SymL(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="isNaN"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isNaN")
        ),
        @Meta.SymL(
          offset=68828, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="isNumber"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="isNumber")
        ),
        @Meta.SymL(
          offset=71888, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="maxBound"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded_Long", member="maxBound")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="gcdHelper"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="gcdHelper")
        ),
        @Meta.SymL(
          offset=17331, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="hashCode")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="fromIntegral"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="fromIntegral")
        ),
        @Meta.SymL(
          offset=70021, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="fromInteger"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="fromInteger")
        ),
        @Meta.SymL(
          offset=72134, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="from"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="from")
        ),
        @Meta.SymL(
          offset=69998, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="fromInt"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="fromInt")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="gcd"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="gcd")
        ),
        @Meta.SymL(
          offset=69257, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="one"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="one")
        ),
        @Meta.SymL(
          offset=73597, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="even"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="even")
        ),
        @Meta.SymL(
          offset=72541, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="enumFromTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromTo")
        ),
        @Meta.SymL(
          offset=72702, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="enumFromThen"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromThen")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="div"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="div")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="divMod"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="divMod")
        ),
        @Meta.SymL(
          offset=72657, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="enumFrom"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFrom")
        ),
        @Meta.SymL(
          offset=72783,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="enumFromThenTo"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Long", member="enumFromThenTo")
        ),
        @Meta.SymL(
          offset=68241, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="compare"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="compare")
        ),
        @Meta.SymL(
          offset=73677, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="big"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="big")
        ),
        @Meta.SymL(
          offset=73462, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="^"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral_Long", member="^")
        ),
        @Meta.SymL(
          offset=68696, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member=">"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member=">")
        ),
        @Meta.SymL(
          offset=68466, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member=">="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member=">=")
        ),
        @Meta.SymL(
          offset=69557, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="abs"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="abs")
        ),
        @Meta.SymL(
          offset=68732, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="<=>"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<=>")
        ),
        @Meta.SymL(
          offset=68581, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="<"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<")
        ),
        @Meta.SymL(
          offset=68350, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="<="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Long", member="<=")
        ),
        @Meta.SymL(
          offset=69158, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="-"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="-")
        ),
        @Meta.SymL(
          offset=68208, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="!=")
        ),
        @Meta.SymL(
          offset=69039, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="*"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="*")
        ),
        @Meta.SymL(
          offset=68929, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="+"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Long", member="+")
        ),
        @Meta.SymL(
          offset=68092, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Long", member="==")
        )
      },
      funs={
        @Meta.SymV(
          offset=16660, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="ushiftR"),
          stri="s(ss)", sig=246, nativ=">>>", pur=true, depth=2, rkind=33, doc=" unsigned right shift   ", op=67
        ),
        @Meta.SymV(
          offset=16579, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="shiftR"),
          stri="s(ss)", sig=246, nativ=">>", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=17240, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="size"),
          stri="s", sig=16, nativ="java.lang.Long.SIZE", pur=true, depth=0, rkind=33
        ),
        @Meta.SymV(
          offset=17165, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="rotateR"),
          stri="s(ss)", sig=246, nativ="java.lang.Long.rotateRight", pur=true, depth=2, rkind=33,
          doc=" Returns the value obtained by rotating the two's complement binary representation   " +"\n"+
          "" +"\n"+ " of the specified long value right by the specified number of bits.   ",
          op=67
        ),
        @Meta.SymV(
          offset=16926, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="rotateL"),
          stri="s(ss)", sig=246, nativ="java.lang.Long.rotateLeft", pur=true, depth=2, rkind=33,
          doc=" Returns the value obtained by rotating the two's complement binary representation   " +"\n"+
          "" +"\n"+ " of the specified long value left by the specified number of bits.   ",
          op=67
        ),
        @Meta.SymV(
          offset=16527, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="shiftL"),
          stri="s(ss)", sig=246, nativ="<<", pur=true, depth=2, rkind=33, op=67
        ),
        @Meta.SymV(
          offset=16079, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="int"),
          stri="s(s)", sig=81, nativ="(int)", pur=true, depth=1, rkind=33,
          doc=" Uses a java cast to convert a 'Long' to an 'Int'. This is a _narrowing primitive conversion_ in java parlance.   "
        ),
        @Meta.SymV(
          offset=15894, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="double"),
          stri="s(s)", sig=247, nativ="java.lang.Double.valueOf", pur=true, depth=1, rkind=33,
          doc=" Convert an 'Long' to a 'Double', i.e. @42L.double == 42.0@.   " +"\n"+
          "" +"\n"+ " For large numbers, the result may have been be rounded.   "
        ),
        @Meta.SymV(
          offset=16714, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="complement"),
          stri="s(s)", sig=83, nativ="~", pur=true, depth=1, rkind=33
        ),
        @Meta.SymV(
          offset=16352, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member=".^."),
          stri="s(ss)", sig=57, nativ="^", pur=true, depth=2, rkind=33,
          doc=" Computes binary _exclusive or_ of two long integers. Uses the java @^@-operator.   ", op=65
        ),
        @Meta.SymV(
          offset=16477, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member=".&."),
          stri="s(ss)", sig=57, nativ="&", pur=true, depth=2, rkind=33,
          doc=" Computes binary _and_ of two integers. Uses the java @&@-operator.   ", op=66
        ),
        @Meta.SymV(
          offset=16212, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member=".|."),
          stri="s(ss)", sig=57, nativ="|", pur=true, depth=2, rkind=33,
          doc=" Computes binary _or_ of two long integers. Uses the java @|@-operator.   ", op=65
        ),
        @Meta.SymV(
          offset=15697, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Long", member="float"),
          stri="s(s)", sig=248, nativ="java.lang.Float.valueOf", pur=true, depth=1, rkind=33,
          doc=" Convert an 'Long' to a 'Float', i.e. @42L.float == 42.0f@.   " +"\n"+ ""
          +"\n"+ " For large numbers, the result may have been be rounded.   "
        )
      },
      pur=true, nativ="long",
      doc="" +"\n"+ " * 'Long' values are based on Java's primitive @long@ values." +"\n"+
      " *" +"\n"+ " * According to the Java Language Specification, @long@ values are" +"\n"+
      "   64 bit wide signed two's complement integers (§4.2). Java operations on @long@ do" +"\n"+
      "   not indicate overflow or underflow in any way (§4.2.2). Instead, just the low 64 bits of" +"\n"+
      "   every result are retained." +"\n"+ "      "
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,)"), typ=249,
      kind=150,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=250,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false), @Meta.Field(offset=0, sigma=233, strict=false),
            @Meta.Field(offset=0, sigma=234, strict=false)
          },
          doc="23-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="23-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,)"), typ=251,
      kind=151,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,)",
            member="(,,,,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=252,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false), @Meta.Field(offset=0, sigma=233, strict=false),
            @Meta.Field(offset=0, sigma=234, strict=false), @Meta.Field(offset=0, sigma=235, strict=false)
          },
          doc="24-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="24-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,)"), typ=253,
      kind=147,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=254,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false)
          },
          doc="20-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="20-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,)"), typ=255,
      kind=148,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=256,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false)
          },
          doc="21-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="21-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,)"), typ=257,
      kind=149,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=258,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false), @Meta.Field(offset=0, sigma=233, strict=false)
          },
          doc="22-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="22-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,)"), typ=259,
      kind=144,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=260,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false)
          },
          doc="17-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="17-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,)"), typ=261,
      kind=145,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=262,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false)
          },
          doc="18-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="18-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,)"), typ=263,
      kind=141,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,)"),
          cid=0, typ=264,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false)
          },
          doc="14-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="14-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,)"), typ=265,
      kind=142,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=266,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false)
          },
          doc="15-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="15-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,)"), typ=267,
      kind=143,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=268,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false)
          },
          doc="16-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="16-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,)"), typ=269,
      kind=146,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=270,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false)
          },
          doc="19-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="19-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,)"), typ=271,
      kind=138,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,)", member="(,,,,,,,,,,)"), cid=0,
          typ=272,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false)
          },
          doc="11-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="11-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,)"), typ=273,
      kind=139,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,)", member="(,,,,,,,,,,,)"),
          cid=0, typ=274,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false)
          },
          doc="12-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="12-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,)"), typ=275,
      kind=135,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,)", member="(,,,,,,,)"),
          cid=0, typ=276,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false)
          },
          doc="8-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="8-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,)"), typ=277,
      kind=136,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,)", member="(,,,,,,,,)"),
          cid=0, typ=278,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false)
          },
          doc="9-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="9-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,)"), typ=279,
      kind=137,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,)", member="(,,,,,,,,,)"), cid=0,
          typ=280,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false)
          },
          doc="10-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="10-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,)"), typ=281,
      kind=132,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,)", member="(,,,,)"), cid=0,
          typ=282,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false)
          },
          doc="5-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="5-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,)"), typ=283,
      kind=133,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,)", member="(,,,,,)"), cid=0,
          typ=284,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false)
          },
          doc="6-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="6-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,)"), typ=285,
      kind=76,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,)", member="(,)"), cid=0,
          typ=286,
          fields={@Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false)},
          doc="2-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="2-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,)"), typ=287,
      kind=130,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,)", member="(,,)"), cid=0,
          typ=288,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false)
          },
          doc="3-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="3-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,)"), typ=289,
      kind=131,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,)", member="(,,,)"), cid=0,
          typ=290,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false)
          },
          doc="4-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="4-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,)"), typ=291,
      kind=134,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,)", member="(,,,,,,)"),
          cid=0, typ=292,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false)
          },
          doc="7-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="7-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,)"), typ=293,
      kind=140,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,)", member="(,,,,,,,,,,,,)"),
          cid=0, typ=294,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false)
          },
          doc="13-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="13-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="()"), typ=110,
      kind=3,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="()", member="()"), cid=0,
          typ=110, fields={}, doc="Unit value"
        )
      },
      lnks={
        @Meta.SymL(
          offset=57552, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="()", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="hashCode")
        ),
        @Meta.SymL(
          offset=57535, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="()", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="==")
        ),
        @Meta.SymL(
          offset=57514, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="()", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_()", member="!=")
        )
      },
      funs={}, prod=true, isEnum=true, doc="Unit type"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,)"), typ=295,
      kind=152,
      cons={
        @Meta.SymD(
          offset=0,
          name=@Meta.QName(
            kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,)",
            member="(,,,,,,,,,,,,,,,,,,,,,,,,)"
          ),
          cid=0, typ=296,
          fields={
            @Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=156, strict=false),
            @Meta.Field(offset=0, sigma=157, strict=false), @Meta.Field(offset=0, sigma=158, strict=false),
            @Meta.Field(offset=0, sigma=31, strict=false), @Meta.Field(offset=0, sigma=159, strict=false),
            @Meta.Field(offset=0, sigma=160, strict=false), @Meta.Field(offset=0, sigma=161, strict=false),
            @Meta.Field(offset=0, sigma=22, strict=false), @Meta.Field(offset=0, sigma=225, strict=false),
            @Meta.Field(offset=0, sigma=226, strict=false), @Meta.Field(offset=0, sigma=227, strict=false),
            @Meta.Field(offset=0, sigma=228, strict=false), @Meta.Field(offset=0, sigma=9, strict=false),
            @Meta.Field(offset=0, sigma=18, strict=false), @Meta.Field(offset=0, sigma=229, strict=false),
            @Meta.Field(offset=0, sigma=230, strict=false), @Meta.Field(offset=0, sigma=4, strict=false),
            @Meta.Field(offset=0, sigma=185, strict=false), @Meta.Field(offset=0, sigma=231, strict=false),
            @Meta.Field(offset=0, sigma=232, strict=false), @Meta.Field(offset=0, sigma=233, strict=false),
            @Meta.Field(offset=0, sigma=234, strict=false), @Meta.Field(offset=0, sigma=235, strict=false),
            @Meta.Field(offset=0, sigma=236, strict=false)
          },
          doc="25-tuple constructor"
        )
      },
      lnks={}, funs={}, prod=true, doc="25-tuple"
    ),
    @Meta.SymT(
      offset=0, name=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="[]"), typ=297,
      kind=61,
      cons={
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="[]"), cid=0,
          typ=297, fields={}, doc="empty list"
        ),
        @Meta.SymD(
          offset=0, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member=":"), cid=1,
          typ=299,
          fields={@Meta.Field(offset=0, sigma=26, strict=false), @Meta.Field(offset=0, sigma=298, strict=false)},
          doc="list construction", op=76
        )
      },
      lnks={
        @Meta.SymL(
          offset=79328, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="hashCode"),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="hashCode")
        ),
        @Meta.SymL(
          offset=79065, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="!="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="!=")
        ),
        @Meta.SymL(
          offset=79195, name=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="=="),
          alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_[]", member="==")
        )
      },
      funs={}, doc="list type"
    )
  },
  symvs={
    @Meta.SymV(
      offset=50566, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="||"), stri="s(ss)",
      sig=71, nativ="||", pur=true, depth=2, rkind=33,
      doc=" " +"\n"+ "    The Java @||@ operator on booleans." +"\n"+ "    " +"\n"+ "    Note that since this is a"
      +"\n"+ "    native function, the second argument appears strict to the compiler" +"\n"+
      "    when in fact it is lazy at the Java level." +"\n"+ "    " +"\n"+
      "    This can lead to inconsistent results in cases where the wrong strictness of" +"\n"+
      "    the second argument propagates to arguments of the function that contains " +"\n"+
      "    an application of '||'. (The documentation for '&&' has an example.)" +"\n"+
      "    " +"\n"+ "    See 'oder' for an alternative" +"\n"+ "         ",
      op=77
    ),
    @Meta.SymV(
      offset=81555, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="using"), stri="s(suu)",
      sig=301, depth=3, rkind=49,
      doc="" +"\n"+ " * @using f@ applies a projection function _f_ on both sides of '=='." +"\n"+
      " * Example usage:" +"\n"+ " * > uniqBy (using fst) [(1, 1), (2, 2), (2, 3), (3,4), (2,5)]" +"\n"+
      "      "
    ),
    @Meta.SymV(
      offset=50908, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="und"), stri="s(su)",
      sig=71, depth=2, rkind=49, expr=416,
      doc=" Like '&&', but second argument is lazy." +"\n"+
      "    The @`und`@ operator has the same precedence and arity as '&&'." +"\n"+ "    The definition is"
      +"\n"+ "    > a `und` b = if a then b     else false" +"\n"+
      "    This should really be named _and_, but Haskell 2010 uses this already for lists." +"\n"+
      "    Hence we use the german word _und_." +"\n"+ "         ",
      op=78
    ),
    @Meta.SymV(
      offset=48208, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="undefined"), stri="u",
      sig=302, depth=0, rkind=36, doc=" This is the standard undefined value.   "
    ),
    @Meta.SymV(
      offset=86240, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="traceStrLn"), stri="s(s)",
      sig=141, nativ="java.lang.System.err.println", depth=1, rkind=33,
      doc=" warning: use @stderr.println@ instead   " +"\n"+ "" +"\n"+
      " print a 'String' to the standard error stream and append a new line.   "
    ),
    @Meta.SymV(
      offset=86058, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="traceStr"), stri="s(s)",
      sig=141, nativ="java.lang.System.err.print", depth=1, rkind=33,
      doc=" warning: use @stderr.print@ instead           " +"\n"+ "" +"\n"+
      " print a 'String' to the standard error stream   "
    ),
    @Meta.SymV(
      offset=34290, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="substr"), stri="s(sss)",
      sig=145, nativ="substring", pur=true, depth=3, rkind=33,
      doc="" +"\n"+ "     @substr s start end@ returns the sub string of @s@ that starts" +"\n"+
      "     with the character at position @start@" +"\n"+ "     and extends to the character at position @end-1@."
      +"\n"+ "" +"\n"+ "    This uses the native method @substring@ of class @java.lang.String@. It" +"\n"+
      "    will throw an @IndexOutOfBoundsException@ if @start@ is negative or larger than" +"\n"+
      "    @end@ or if @end@ is greater than the length of @s@." +"\n"+ "      "
    ),
    @Meta.SymV(
      offset=48550, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="throw"), stri="s(s)",
      sig=303, depth=1, rkind=49, doc=" Constructs an undefined value from a java exception and throws it.   "
    ),
    @Meta.SymV(
      offset=81090, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="uncurry"), stri="s(ss(uu))",
      sig=306, depth=2, rkind=52, doc=" Passes the elements of a 2-tuple as arguments to a function.   "
    ),
    @Meta.SymV(
      offset=41613, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple9"), stri="s(sssssssss)",
      sig=307, nativ="PreludeBase.TTuple9.mk", pur=true, depth=9, rkind=33,
      doc=" Constructs a strict 9-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=41434, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple8"), stri="s(ssssssss)",
      sig=308, nativ="PreludeBase.TTuple8.mk", pur=true, depth=8, rkind=33,
      doc=" Constructs a strict 8-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=40939, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple5"), stri="s(sssss)",
      sig=309, nativ="PreludeBase.TTuple5.mk", pur=true, depth=5, rkind=33,
      doc=" Constructs a strict 5-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=41097, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple6"), stri="s(ssssss)",
      sig=310, nativ="PreludeBase.TTuple6.mk", pur=true, depth=6, rkind=33,
      doc=" Constructs a strict 6-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=41262, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple7"), stri="s(sssssss)",
      sig=311, nativ="PreludeBase.TTuple7.mk", pur=true, depth=7, rkind=33,
      doc=" Constructs a strict 7-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=34726, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strtail"), stri="s(ss)",
      sig=312, nativ="substring", pur=true, depth=2, rkind=33,
      doc="" +"\n"+ "    @strtail s n@ returns a new string that is a substring of string _s_." +"\n"+
      "    The substring begins with the character at the specified index" +"\n"+ "    and extends to the end of _s_."
      +"\n"+ "" +"\n"+ "    This uses the native method @substring@ of class @java.lang.String@. It" +"\n"+
      "    will throw an @IndexOutOfBoundsException@ if _n_ is negative or larger than" +"\n"+
      "    the length of _s_." +"\n"+ "     "
    ),
    @Meta.SymV(
      offset=45760, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple26"),
      stri="s(ssssssssssssssssssssssssss)", sig=313, nativ="PreludeBase.TTuple26.mk", pur=true,
      depth=26, rkind=33, doc=" Constructs a strict 26-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=40644, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple3"), stri="s(sss)",
      sig=314, nativ="PreludeBase.TTuple3.mk", pur=true, depth=3, rkind=33,
      doc=" Constructs a strict 3-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=45167, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple24"),
      stri="s(ssssssssssssssssssssssss)", sig=315, nativ="PreludeBase.TTuple24.mk", pur=true,
      depth=24, rkind=33, doc=" Constructs a strict 24-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=44602, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple22"),
      stri="s(ssssssssssssssssssssss)", sig=316, nativ="PreludeBase.TTuple22.mk", pur=true,
      depth=22, rkind=33, doc=" Constructs a strict 22-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=44881, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple23"),
      stri="s(sssssssssssssssssssssss)", sig=317, nativ="PreludeBase.TTuple23.mk", pur=true,
      depth=23, rkind=33, doc=" Constructs a strict 23-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=45460, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple25"),
      stri="s(sssssssssssssssssssssssss)", sig=318, nativ="PreludeBase.TTuple25.mk", pur=true,
      depth=25, rkind=33, doc=" Constructs a strict 25-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=44065, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple20"),
      stri="s(ssssssssssssssssssss)", sig=319, nativ="PreludeBase.TTuple20.mk", pur=true,
      depth=20, rkind=33, doc=" Constructs a strict 20-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=43807, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple19"),
      stri="s(sssssssssssssssssss)", sig=320, nativ="PreludeBase.TTuple19.mk", pur=true, depth=19,
      rkind=33, doc=" Constructs a strict 19-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=40507, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple2"), stri="s(ss)",
      sig=321, nativ="PreludeBase.TTuple2.mk", pur=true, depth=2, rkind=33,
      doc="" +"\n"+ " * Used to construct a strict tuple. Don't use anymore!" +"\n"+ "      "
    ),
    @Meta.SymV(
      offset=44330, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple21"),
      stri="s(sssssssssssssssssssss)", sig=322, nativ="PreludeBase.TTuple21.mk", pur=true,
      depth=21, rkind=33, doc=" Constructs a strict 21-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=40788, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple4"), stri="s(ssss)",
      sig=323, nativ="PreludeBase.TTuple4.mk", pur=true, depth=4, rkind=33,
      doc=" Constructs a strict 4-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=43075, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple16"),
      stri="s(ssssssssssssssss)", sig=324, nativ="PreludeBase.TTuple16.mk", pur=true, depth=16,
      rkind=33, doc=" Constructs a strict 16-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=43312, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple17"),
      stri="s(sssssssssssssssss)", sig=325, nativ="PreludeBase.TTuple17.mk", pur=true, depth=17,
      rkind=33, doc=" Constructs a strict 17-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=42406, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple13"), stri="s(sssssssssssss)",
      sig=326, nativ="PreludeBase.TTuple13.mk", pur=true, depth=13, rkind=33,
      doc=" Constructs a strict 13-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=42622, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple14"), stri="s(ssssssssssssss)",
      sig=327, nativ="PreludeBase.TTuple14.mk", pur=true, depth=14, rkind=33,
      doc=" Constructs a strict 14-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=42845, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple15"), stri="s(sssssssssssssss)",
      sig=328, nativ="PreludeBase.TTuple15.mk", pur=true, depth=15, rkind=33,
      doc=" Constructs a strict 15-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=41995, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple11"), stri="s(sssssssssss)",
      sig=329, nativ="PreludeBase.TTuple11.mk", pur=true, depth=11, rkind=33,
      doc=" Constructs a strict 11-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=80165, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="snd"), stri="s(s(us))",
      sig=331, depth=1, rkind=49, doc=" return the second element of a 2-tuple   "
    ),
    @Meta.SymV(
      offset=41800, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple10"), stri="s(ssssssssss)",
      sig=332, nativ="PreludeBase.TTuple10.mk", pur=true, depth=10, rkind=33,
      doc=" Constructs a strict 10-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=42197, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple12"), stri="s(ssssssssssss)",
      sig=333, nativ="PreludeBase.TTuple12.mk", pur=true, depth=12, rkind=33,
      doc=" Constructs a strict 12-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=86402, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="printStr"), stri="s(s)",
      sig=141, nativ="java.lang.System.out.print", depth=1, rkind=33,
      doc=" warning: use @stdout.print@ instead   " +"\n"+ "" +"\n"+
      " print a 'String' to the standard output stream   "
    ),
    @Meta.SymV(
      offset=86585, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="printStrLn"), stri="s(s)",
      sig=141, nativ="java.lang.System.out.println", depth=1, rkind=33,
      doc=" warning: use @stdout.println@ instead   " +"\n"+ "" +"\n"+
      " print a 'String' to the standard output stream and append a new line.   "
    ),
    @Meta.SymV(
      offset=46106, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="seq"), stri="s(ss)",
      sig=334, depth=2, rkind=49,
      doc=" @a `seq` b@ evaluates _a_ before it returns _b_   " +"\n"+ "" +"\n"+
      " This is a right associative operator with precedence 15.   ",
      op=74
    ),
    @Meta.SymV(
      offset=82410, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="on"), stri="s(ssuu)",
      sig=338, depth=4, rkind=52,
      doc=" > g `on` f    " +"\n"+ "" +"\n"+ " Apply a projection function @f@ on both operands of @g@   " +"\n"+
      "" +"\n"+ " > comparing f = (<=>) `on` f   ",
      op=56
    ),
    @Meta.SymV(
      offset=51258, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="oder"), stri="s(su)",
      sig=71, depth=2, rkind=49, expr=421,
      doc=" Like '||', but second argument is lazy." +"\n"+
      "    The @`oder`@ operator has the same precedence and arity as '||'." +"\n"+ "    The definition is"
      +"\n"+ "    > a `oder`  b = if a then true  else b" +"\n"+
      "    This should really be named _or_, but Haskell 2010 uses this already for lists." +"\n"+
      "    Hence we use the german word _oder_." +"\n"+ "         ",
      op=77
    ),
    @Meta.SymV(
      offset=79817, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="maybe"), stri="s(uss)",
      sig=341, depth=3, rkind=52,
      doc="" +"\n"+ "    The 'maybe' function takes a default value, a function, and a 'Maybe' value. " +"\n"+
      "    If the 'Maybe' value is 'Nothing', the function returns the default value. " +"\n"+
      "    Otherwise, it applies the function to the value inside the 'Just' and returns the result." +"\n"+
      "         "
    ),
    @Meta.SymV(
      offset=51393, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="not"), stri="s(s)",
      sig=126, nativ="!", pur=true, depth=1, rkind=33,
      doc="" +"\n"+ "    @not b@ is true if _b_ is false, otherwise true. Uses java's '!' operator." +"\n"+
      "      "
    ),
    @Meta.SymV(
      offset=8253, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="otherwise"), stri="s",
      sig=70, depth=0, rkind=49, expr=75,
      doc="" +"\n"+ "This is a constant with the value @true@." +"\n"+
      "It is most often used as the last alternative in pattern guards:" +"\n"+ ""
      +"\n"+ "> foo n | n >= 0    = ..." +"\n"+ ">       | otherwise = ..." +"\n"+ "     "
    ),
    @Meta.SymV(
      offset=43556, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="strictTuple18"),
      stri="s(ssssssssssssssssss)", sig=342, nativ="PreludeBase.TTuple18.mk", pur=true, depth=18,
      rkind=33, doc=" Constructs a strict 18-tuple. See remarks for 'strictTuple2'.   "
    ),
    @Meta.SymV(
      offset=80342, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="id"), stri="s(s)",
      sig=343, depth=1, rkind=49, publik=false
    ),
    @Meta.SymV(
      offset=80106, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="fst"), stri="s(s(su))",
      sig=345, depth=1, rkind=49, doc=" return the first element of a 2-tuple   "
    ),
    @Meta.SymV(
      offset=81005, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="flip"), stri="s(suu)",
      sig=346, depth=3, rkind=52, expr=429,
      doc=" Exchange first and second argument of a function, i.e.   " +"\n"+ "" +"\n"+
      " > flip f a b = f b a   "
    ),
    @Meta.SymV(
      offset=48377, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="error"), stri="s(s)",
      sig=347, depth=1, rkind=185,
      doc=" Construct an undefined value with an informative message.   " +"\n"+ ""
      +"\n"+ " When evaluated, an 'Undefined' exception will be thrown.   "
    ),
    @Meta.SymV(
      offset=79987, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="either"), stri="s(sss)",
      sig=350, depth=3, rkind=52,
      doc=" apply first function to value in 'Left' or second function to value in 'Right'   ", op=57
    ),
    @Meta.SymV(
      offset=82048, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="descending"), stri="s(suu)",
      sig=351, depth=3, rkind=49,
      doc="" +"\n"+ " * @descending f@ applies a projection function on both sides of '<=>', but flips arguments."
      +"\n"+ " * Example usage:" +"\n"+
      " * > sortBy (descending fst) [(1, \"z\"), (2, \"b\")] == [(2, \"b\"), (1, \"z\")]" +"\n"+
      "      "
    ),
    @Meta.SymV(
      offset=76876, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="ctos"), stri="s(s)",
      sig=352, nativ="java.lang.Character.toString", pur=true, depth=1, rkind=33,
      doc=" make a 'String' from a single 'Char'   "
    ),
    @Meta.SymV(
      offset=81177, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="curry"), stri="s(suu)",
      sig=354, depth=3, rkind=52, expr=438, doc=" @curry f@ passes the next two arguments as 2-tuple to _f_   "
    ),
    @Meta.SymV(
      offset=81757, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="comparing"), stri="s(suu)",
      sig=351, depth=3, rkind=49,
      doc="" +"\n"+ " * @comparing f@ applies a projection function on both sides of '<=>'." +"\n"+
      " * Example usage:" +"\n"+ " * > sortBy (comparing snd) [(1, \"z\"), (2, \"b\")] == [(2, \"b\"), (1, \"z\")]"
      +"\n"+ "      "
    ),
    @Meta.SymV(
      offset=80765, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="const"), stri="s(su)",
      sig=355, depth=2, rkind=49, expr=442,
      doc=" @const a@ is a function that returns _a_ regardless of its argument.   "
    ),
    @Meta.SymV(
      offset=76802, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="chr"), stri="s(s)",
      sig=122, depth=1, rkind=49, expr=446
    ),
    @Meta.SymV(
      offset=8749, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="bool"), stri="s(uus)",
      sig=356, depth=3, rkind=49, expr=453,
      doc="" +"\n"+ "This is used by code generation when a conditional expression" +"\n"+
      "appears in a lazy context, i.e." +"\n"+ "> (42, if foo then bar else baz)" +"\n"+
      "@bool a b c@ evaluates to @a@ if @c@ is @true@, otherwise to @b@." +"\n"+ "     "
    ),
    @Meta.SymV(
      offset=80879, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="asTypeOf"), stri="s(su)",
      sig=357, depth=2, rkind=49, expr=457,
      doc=" @asTypeOf a b@ is _a_  with the type of _b_.   " +"\n"+ "" +"\n"+
      " This is a type restricted version of 'const'.   "
    ),
    @Meta.SymV(
      offset=51529, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="^^"), stri="s(ss)",
      sig=71, depth=2, rkind=49,
      doc="" +"\n"+ " * @a ^^ b@ is true if either _a_ or _b_ is true, but not both." +"\n"+
      " * > a ^^ b = if a then not b else b" +"\n"+ "      ",
      op=77
    ),
    @Meta.SymV(
      offset=34786, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="atoi"), stri="s(s)",
      sig=105, depth=1, rkind=49
    ),
    @Meta.SymV(
      offset=6895, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="constructor"), stri="s(s)",
      sig=358, nativ="frege.run.RunTM.constructor", pur=true, depth=1, rkind=33,
      doc="" +"\n"+ " * Determines the constructor of a value." +"\n"+ " * This is used like" +"\n"+
      " * >constructor arg" +"\n"+ " * where @arg@ is any frege value." +"\n"+ " *" +"\n"+
      " * Returns 0 for function types, product types and native types or" +"\n"+
      " * the _constructor number_ for constructed types. The _constructor number_" +"\n"+
      " * is a small integer associated with every constructed value. It indicates" +"\n"+
      " * the data constructor a value was constructed with." +"\n"+ " *" +"\n"+
      " * The compiler assigns constructor numbers starting from 0 to the constructors" +"\n"+
      " * defined in a @data@ definition in the order of their appearance." +"\n"+ " *"
      +"\n"+ " * Examples" +"\n"+ " * >constructor [] == 0" +"\n"+ " * >constructor (a:as) == 1" +"\n"+
      " * >constructor \"string\"  == 0    // native value" +"\n"+ " *" +"\n"+
      " * This function is strict in its argument, i.e." +"\n"+ " * >constructor undefined == undefined" +"\n"+
      " *" +"\n"+ " * *Implementation specific:* This function is used in derived instances" +"\n"+
      " * of 'Eq' and 'Ord'." +"\n"+ "      "
    ),
    @Meta.SymV(
      offset=8514, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="lazyif"), stri="s(suu)",
      sig=359, depth=3, rkind=49,
      doc="" +"\n"+ "warning: will soon be replaced with bool" +"\n"+
      "This is used by code generation when a conditional expression" +"\n"+ "appears in a lazy context, i.e." +"\n"+
      "> (42, if foo then bar else baz)" +"\n"+ "@lazyif a b c@ evaluates to @b@ if @a@ is @true@, otherwise to @c@."
      +"\n"+ "     "
    ),
    @Meta.SymV(
      offset=81377, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="@"), stri="s(uu)",
      sig=360, depth=2, rkind=49,
      doc=" In patterns, the \\@-operator is used to bind a name to a complex pattern   " +"\n"+
      "" +"\n"+ " > f (x@a:as) = e   " +"\n"+ "" +"\n"+ " is the same as   " +"\n"+ ""
      +"\n"+ " > f arg = case arg of { x -> case x of { a:as -> e }}   ",
      op=74
    ),
    @Meta.SymV(
      offset=51797, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="==="), stri="s(ss)",
      sig=361, nativ="==", pur=true, depth=2, rkind=33,
      doc=" This checks for object identity or equality of primitive values" +"\n"+ "    using Java's @==@ operator."
      +"\n"+ "    It evaluates its arguments, so undefined values cannot be compared.      ",
      op=96
    ),
    @Meta.SymV(
      offset=48805, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="?"), stri="s(s)",
      sig=86, nativ="~", pur=true, depth=1, rkind=33, doc=" complement   "
    ),
    @Meta.SymV(
      offset=80679, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="$!"), stri="s(ss)",
      sig=362, depth=2, rkind=52, expr=463, doc=" Same as `$` but argument is strict   ", op=73
    ),
    @Meta.SymV(
      offset=50043, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="&&"), stri="s(ss)",
      sig=71, nativ="&&", pur=true, depth=2, rkind=33,
      doc=" The Java @&&@ operator on booleans. Note that since this is a" +"\n"+
      "    native function, the second argument appears strict to the compiler" +"\n"+
      "    when in fact it is lazy at the Java level." +"\n"+
      "    This can lead to inconsistent results in some cases. For example, the following" +"\n"+
      "    program correctly prints @false@ in the first line of the output, but then aborts:" +"\n"+
      "    > main _ = do" +"\n"+ "    >    stdout << (false && undefined) << \"\\n\"" +"\n"+
      "    >    stdout << conj false undefined << \"\\n\"" +"\n"+ "    >  where" +"\n"+
      "    >    conj a b = a && b" +"\n"+ "" +"\n"+
      "    Note that the very same behaviour is seen in the following java program" +"\n"+
      "" +"\n"+ "    > public class And {" +"\n"+ "    >    static boolean undef() {" +"\n"+
      "    >        if (true) throw new Error(\"undefined\");" +"\n"+ "    >        return false;" +"\n"+
      "    >    }" +"\n"+ "    >    static boolean conj(boolean a, boolean b) { return a&&b; }" +"\n"+
      "    >    public static void main(String[] args) {" +"\n"+ "    >        System.out.println(false && undef());"
      +"\n"+ "    >        System.out.println(conj(false, undef()));" +"\n"+ "    >    }" +"\n"+
      "    > }" +"\n"+ "" +"\n"+ "    One could thus say that '&&' behaves exactly like the Java operator including"
      +"\n"+ "    the fact that it cannot be replaced by a function without changing the semantics" +"\n"+
      "    of a program." +"\n"+ "" +"\n"+ "    For an alternative see 'und'" +"\n"+ "         ",
      op=78
    ),
    @Meta.SymV(
      offset=52013, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="!=="), stri="s(ss)",
      sig=361, nativ="!=", pur=true, depth=2, rkind=33,
      doc=" This checks for object identity or inequality of primitive values" +"\n"+ "    using Java's @!=@ operator."
      +"\n"+ "    It evaluates its arguments, so undefined values cannot be compared.      ",
      op=96
    ),
    @Meta.SymV(
      offset=80625, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="$"), stri="s(su)",
      sig=362, depth=2, rkind=52, expr=468,
      doc="" +"\n"+ "    @a $ b@ is the same as @a b@, but because of '$''s low precedence" +"\n"+
      "    one can write @f $ x+y@ instead of @f (x+y)@. Also, because '$' is right" +"\n"+
      "    associative, @f $ g $ h y@ is @f (g (h y))@" +"\n"+ "     ",
      op=73
    ),
    @Meta.SymV(
      offset=80262, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="!:"), stri="s(su)",
      sig=363, depth=2, rkind=49,
      doc=" A head strict variant of (:)   " +"\n"+ "" +"\n"+ " This will be used in list comprehensions   ", op=76
    ),
    @Meta.SymV(
      offset=48721, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="!"), stri="s(s)",
      sig=126, nativ="!", pur=true, depth=1, rkind=33, doc=" The Java @!@ operator on booleans   "
    )
  },
  symls={
    @Meta.SymL(
      offset=58443, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="zero"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="zero")
    ),
    @Meta.SymL(
      offset=62392, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="toInteger"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="big")
    ),
    @Meta.SymL(
      offset=56497, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="succ"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="succ")
    ),
    @Meta.SymL(
      offset=58008, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="subtract"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="subtract")
    ),
    @Meta.SymL(
      offset=58316, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="sign"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="sign")
    ),
    @Meta.SymL(
      offset=62970, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="rem"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="rem")
    ),
    @Meta.SymL(
      offset=62570, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="quot"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="quot")
    ),
    @Meta.SymL(
      offset=62624, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="quotRem"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="quotRem")
    ),
    @Meta.SymL(
      offset=65548, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="powg"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powg")
    ),
    @Meta.SymL(
      offset=60532, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="positiveInfinity"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="positiveInfinity")
    ),
    @Meta.SymL(
      offset=65323, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="powf"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powf")
    ),
    @Meta.SymL(
      offset=56602, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="pred"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="pred")
    ),
    @Meta.SymL(
      offset=55909, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="ord"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="ord")
    ),
    @Meta.SymL(
      offset=65261, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="odd"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="odd")
    ),
    @Meta.SymL(
      offset=58383, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="one"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="one")
    ),
    @Meta.SymL(
      offset=60550, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="negativeInfinity"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="negativeInfinity")
    ),
    @Meta.SymL(
      offset=60568, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="nan"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="nan")
    ),
    @Meta.SymL(
      offset=70252, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="minBound"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded", member="minBound")
    ),
    @Meta.SymL(
      offset=62993, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="mod"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="mod")
    ),
    @Meta.SymL(
      offset=58239, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="negate"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="negate")
    ),
    @Meta.SymL(
      offset=70294, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="maxBound"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Bounded", member="maxBound")
    ),
    @Meta.SymL(
      offset=64854, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="lcm"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="lcm")
    ),
    @Meta.SymL(
      offset=54030, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="max"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="max")
    ),
    @Meta.SymL(
      offset=54098, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="min"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="min")
    ),
    @Meta.SymL(
      offset=60171, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="isNumber"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isNumber")
    ),
    @Meta.SymL(
      offset=59164, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="isInfinite"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isInfinite")
    ),
    @Meta.SymL(
      offset=59677, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="isNaN"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isNaN")
    ),
    @Meta.SymL(
      offset=64688, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="gcdHelper"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcdHelper")
    ),
    @Meta.SymL(
      offset=58730, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="fromInteger"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInteger")
    ),
    @Meta.SymL(
      offset=63950, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="fromIntegral"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="fromIntegral")
    ),
    @Meta.SymL(
      offset=64683, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="gcd"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcd")
    ),
    @Meta.SymL(
      offset=5843, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="hashCode"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="hashCode")
    ),
    @Meta.SymL(
      offset=60440, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="fromDouble"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="fromDouble")
    ),
    @Meta.SymL(
      offset=56389, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="from"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="from")
    ),
    @Meta.SymL(
      offset=57237, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="enumFromTo"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromTo")
    ),
    @Meta.SymL(
      offset=65255, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="even"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="even")
    ),
    @Meta.SymL(
      offset=58645, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="fromInt"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInt")
    ),
    @Meta.SymL(
      offset=57167, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="enumFromThen"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromThen")
    ),
    @Meta.SymL(
      offset=57420, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="enumFrom"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFrom")
    ),
    @Meta.SymL(
      offset=62565, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="div"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="div")
    ),
    @Meta.SymL(
      offset=62633, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="divMod"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="divMod")
    ),
    @Meta.SymL(
      offset=63870, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="big"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="big")
    ),
    @Meta.SymL(
      offset=53234, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="compare"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="compare")
    ),
    @Meta.SymL(
      offset=81832, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="ascending"),
      alias=@Meta.QName(pack="frege.prelude.PreludeBase", base="comparing")
    ),
    @Meta.SymL(
      offset=58139, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="abs"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="abs")
    ),
    @Meta.SymL(
      offset=65099, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="^"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="^")
    ),
    @Meta.SymL(
      offset=8036, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="True"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="HaskellBool", member="True")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="[]"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="[]")
    ),
    @Meta.SymL(
      offset=57088, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="enumFromThenTo"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromThenTo")
    ),
    @Meta.SymL(
      offset=79895, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Right"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Either", member="Right")
    ),
    @Meta.SymL(
      offset=79531, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Nothing"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Maybe", member="Nothing")
    ),
    @Meta.SymL(
      offset=79886, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Left"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Either", member="Left")
    ),
    @Meta.SymL(
      offset=79541, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Just"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Maybe", member="Just")
    ),
    @Meta.SymL(
      offset=52192, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Lt"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Lt")
    ),
    @Meta.SymL(
      offset=7967, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="False"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="HaskellBool", member="False")
    ),
    @Meta.SymL(
      offset=52197, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Eq"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Eq")
    ),
    @Meta.SymL(
      offset=53960, name=@Meta.QName(pack="frege.prelude.PreludeBase", base=">="),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">=")
    ),
    @Meta.SymL(
      offset=53763, name=@Meta.QName(pack="frege.prelude.PreludeBase", base=">"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">")
    ),
    @Meta.SymL(
      offset=52202, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="Gt"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Gt")
    ),
    @Meta.SymL(
      offset=53601, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="<="),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<=")
    ),
    @Meta.SymL(
      offset=53179, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="<=>"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<=>")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base=":"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member=":")
    ),
    @Meta.SymL(
      offset=57923, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="-"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="-")
    ),
    @Meta.SymL(
      offset=60371, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="/"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Real", member="/")
    ),
    @Meta.SymL(
      offset=58081, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="*"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="*")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,,)",
        member="(,,,,,,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=57853, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="+"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="+")
    ),
    @Meta.SymL(
      offset=53404, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="<"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(
        kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,,,,,,,,,)"
      )
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,)", member="(,,,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,)", member="(,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,)", member="(,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,)", member="(,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,)", member="(,,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,)", member="(,,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,)", member="(,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,)", member="(,,,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,)", member="(,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,)", member="(,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,)", member="(,,,)")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,)", member="(,,,,,,,)")
    ),
    @Meta.SymL(
      offset=4903, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="!="),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="!=")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="()"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="()", member="()")
    ),
    @Meta.SymL(
      offset=0, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,)"),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,)", member="(,,,,,,,,,,,,,,,)")
    ),
    @Meta.SymL(
      offset=4611, name=@Meta.QName(pack="frege.prelude.PreludeBase", base="=="),
      alias=@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="==")
    )
  },
  taus={
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="StringJ")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Char")}),
    @Meta.Tau(kind=0, suba=0, subb=1), @Meta.Tau(kind=9),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="ST")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="RealWorld")}),
    @Meta.Tau(kind=0, suba=4, subb=5),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Either")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Throwable")}),
    @Meta.Tau(kind=0, suba=7, subb=8), @Meta.Tau(suba=3, tvar="r"),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Double")}),
    @Meta.Tau(suba=3, tvar="n"),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Int")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bool")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integer")}),
    @Meta.Tau(suba=3, tvar="o"),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ordering")}),
    @Meta.Tau(suba=3, tvar="i"),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,)")}),
    @Meta.Tau(kind=0, suba=19, subb=18), @Meta.Tau(kind=0, suba=20, subb=18), @Meta.Tau(suba=3, tvar="a"),
    @Meta.Tau(suba=3, tvar="b"), @Meta.Tau(suba=3, tvar="e"),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="[]")}),
    @Meta.Tau(kind=0, suba=25, subb=24),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Float")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Long")}),
    @Meta.Tau(kind=0, suba=19, subb=28), @Meta.Tau(kind=0, suba=29, subb=28), @Meta.Tau(suba=3, tvar="α"),
    @Meta.Tau(kind=0, suba=19, subb=13), @Meta.Tau(kind=0, suba=32, subb=13), @Meta.Tau(kind=0, suba=19, subb=15),
    @Meta.Tau(kind=0, suba=34, subb=15), @Meta.Tau(kind=0, suba=25, subb=22), @Meta.Tau(kind=0, suba=25, subb=31),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="()")}),
    @Meta.Tau(kind=0, suba=25, subb=28), @Meta.Tau(kind=0, suba=25, subb=15), @Meta.Tau(kind=0, suba=25, subb=1),
    @Meta.Tau(kind=0, suba=25, subb=14), @Meta.Tau(kind=0, suba=25, subb=13),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Undefined")}),
    @Meta.Tau(suba=3, tvar="ω"), @Meta.Tau(kind=0, suba=0, subb=22), @Meta.Tau(kind=0, suba=6, subb=38),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="InterruptedException")}
    ),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="NumberFormatException")}
    ),
    @Meta.Tau(kind=0, suba=7, subb=49), @Meta.Tau(kind=0, suba=50, subb=28), @Meta.Tau(kind=0, suba=50, subb=15),
    @Meta.Tau(suba=3, tvar="c"), @Meta.Tau(suba=3, tvar="d"), @Meta.Tau(suba=3, tvar="f"), @Meta.Tau(suba=3, tvar="g"),
    @Meta.Tau(suba=3, tvar="h"), @Meta.Tau(kind=0, suba=50, subb=27), @Meta.Tau(kind=0, suba=50, subb=11),
    @Meta.Tau(kind=0, suba=50, subb=13), @Meta.Tau(kind=8, suba=3, subb=3), @Meta.Tau(suba=3, tvar="s"),
    @Meta.Tau(kind=0, suba=4, subb=62), @Meta.Tau(kind=0, suba=63, subb=22), @Meta.Tau(suba=3, tvar="β"),
    @Meta.Tau(kind=0, suba=4, subb=31), @Meta.Tau(kind=0, suba=66, subb=65), @Meta.Tau(suba=3, tvar="ß"),
    @Meta.Tau(kind=0, suba=4, subb=68), @Meta.Tau(kind=0, suba=69, subb=22), @Meta.Tau(suba=3, tvar="u"),
    @Meta.Tau(kind=0, suba=4, subb=71), @Meta.Tau(kind=0, suba=72, subb=22), @Meta.Tau(kind=0, suba=72, subb=23),
    @Meta.Tau(kind=0, suba=6, subb=22), @Meta.Tau(kind=8, suba=3, subb=61),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Object")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Class")}),
    @Meta.Tau(kind=0, suba=78, subb=22),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Maybe")}),
    @Meta.Tau(kind=0, suba=80, subb=22),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="NoMatch")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="HaskellBool")}),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="GuardFailed")}),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="ClassNotFoundException")}
    ),
    @Meta.Tau(kind=0, suba=7, subb=85), @Meta.Tau(kind=0, suba=86, subb=79), @Meta.Tau(kind=0, suba=6, subb=87),
    @Meta.Tau(kind=0, suba=7, subb=22), @Meta.Tau(kind=0, suba=89, subb=23),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=91, subb=22), @Meta.Tau(kind=0, suba=92, subb=23), @Meta.Tau(kind=0, suba=93, subb=53),
    @Meta.Tau(kind=0, suba=94, subb=54), @Meta.Tau(kind=0, suba=95, subb=24), @Meta.Tau(kind=0, suba=96, subb=55),
    @Meta.Tau(kind=0, suba=97, subb=56), @Meta.Tau(kind=0, suba=98, subb=57), @Meta.Tau(kind=0, suba=99, subb=18),
    @Meta.Tau(suba=3, tvar="j"), @Meta.Tau(kind=0, suba=100, subb=101), @Meta.Tau(suba=3, tvar="k"),
    @Meta.Tau(kind=0, suba=102, subb=103), @Meta.Tau(suba=3, tvar="l"), @Meta.Tau(kind=0, suba=104, subb=105),
    @Meta.Tau(suba=3, tvar="m"), @Meta.Tau(kind=0, suba=106, subb=107), @Meta.Tau(kind=0, suba=108, subb=12),
    @Meta.Tau(kind=0, suba=109, subb=16), @Meta.Tau(suba=3, tvar="p"), @Meta.Tau(kind=0, suba=110, subb=111),
    @Meta.Tau(suba=3, tvar="q"), @Meta.Tau(kind=0, suba=112, subb=113), @Meta.Tau(kind=0, suba=114, subb=10),
    @Meta.Tau(kind=0, suba=115, subb=62), @Meta.Tau(suba=3, tvar="t"), @Meta.Tau(kind=0, suba=116, subb=117),
    @Meta.Tau(kind=0, suba=118, subb=71), @Meta.Tau(suba=3, tvar="v"), @Meta.Tau(kind=0, suba=119, subb=120),
    @Meta.Tau(suba=3, tvar="w"), @Meta.Tau(kind=0, suba=121, subb=122), @Meta.Tau(suba=3, tvar="x"),
    @Meta.Tau(kind=0, suba=123, subb=124), @Meta.Tau(suba=3, tvar="y"), @Meta.Tau(kind=0, suba=125, subb=126),
    @Meta.Tau(suba=3, tvar="z"), @Meta.Tau(kind=0, suba=127, subb=128), @Meta.Tau(kind=8, suba=3, subb=76),
    @Meta.Tau(kind=8, suba=3, subb=130), @Meta.Tau(kind=8, suba=3, subb=131), @Meta.Tau(kind=8, suba=3, subb=132),
    @Meta.Tau(kind=8, suba=3, subb=133), @Meta.Tau(kind=8, suba=3, subb=134), @Meta.Tau(kind=8, suba=3, subb=135),
    @Meta.Tau(kind=8, suba=3, subb=136), @Meta.Tau(kind=8, suba=3, subb=137), @Meta.Tau(kind=8, suba=3, subb=138),
    @Meta.Tau(kind=8, suba=3, subb=139), @Meta.Tau(kind=8, suba=3, subb=140), @Meta.Tau(kind=8, suba=3, subb=141),
    @Meta.Tau(kind=8, suba=3, subb=142), @Meta.Tau(kind=8, suba=3, subb=143), @Meta.Tau(kind=8, suba=3, subb=144),
    @Meta.Tau(kind=8, suba=3, subb=145), @Meta.Tau(kind=8, suba=3, subb=146), @Meta.Tau(kind=8, suba=3, subb=147),
    @Meta.Tau(kind=8, suba=3, subb=148), @Meta.Tau(kind=8, suba=3, subb=149), @Meta.Tau(kind=8, suba=3, subb=150),
    @Meta.Tau(kind=8, suba=3, subb=151), @Meta.Tau(kind=8, suba=3, subb=152),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="->")}),
    @Meta.Tau(kind=0, suba=154, subb=22), @Meta.Tau(kind=0, suba=155, subb=23), @Meta.Tau(kind=0, suba=80, subb=2),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=158, subb=22), @Meta.Tau(kind=0, suba=159, subb=23), @Meta.Tau(kind=0, suba=160, subb=53),
    @Meta.Tau(kind=0, suba=161, subb=54), @Meta.Tau(kind=0, suba=162, subb=24), @Meta.Tau(kind=0, suba=163, subb=55),
    @Meta.Tau(kind=0, suba=164, subb=56), @Meta.Tau(kind=0, suba=165, subb=57), @Meta.Tau(kind=0, suba=166, subb=18),
    @Meta.Tau(kind=0, suba=167, subb=101), @Meta.Tau(kind=0, suba=168, subb=103), @Meta.Tau(kind=0, suba=169, subb=105),
    @Meta.Tau(kind=0, suba=170, subb=107), @Meta.Tau(kind=0, suba=171, subb=12), @Meta.Tau(kind=0, suba=172, subb=16),
    @Meta.Tau(kind=0, suba=173, subb=111), @Meta.Tau(kind=0, suba=174, subb=113), @Meta.Tau(kind=0, suba=175, subb=10),
    @Meta.Tau(kind=0, suba=176, subb=62), @Meta.Tau(kind=0, suba=177, subb=117), @Meta.Tau(kind=0, suba=178, subb=71),
    @Meta.Tau(kind=0, suba=179, subb=120), @Meta.Tau(kind=0, suba=180, subb=122),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=182, subb=22), @Meta.Tau(kind=0, suba=183, subb=23), @Meta.Tau(kind=0, suba=184, subb=53),
    @Meta.Tau(kind=0, suba=185, subb=54), @Meta.Tau(kind=0, suba=186, subb=24), @Meta.Tau(kind=0, suba=187, subb=55),
    @Meta.Tau(kind=0, suba=188, subb=56), @Meta.Tau(kind=0, suba=189, subb=57), @Meta.Tau(kind=0, suba=190, subb=18),
    @Meta.Tau(kind=0, suba=191, subb=101), @Meta.Tau(kind=0, suba=192, subb=103), @Meta.Tau(kind=0, suba=193, subb=105),
    @Meta.Tau(kind=0, suba=194, subb=107), @Meta.Tau(kind=0, suba=195, subb=12), @Meta.Tau(kind=0, suba=196, subb=16),
    @Meta.Tau(kind=0, suba=197, subb=111), @Meta.Tau(kind=0, suba=198, subb=113), @Meta.Tau(kind=0, suba=199, subb=10),
    @Meta.Tau(kind=0, suba=200, subb=62), @Meta.Tau(kind=0, suba=201, subb=117), @Meta.Tau(kind=0, suba=202, subb=71),
    @Meta.Tau(kind=0, suba=203, subb=120), @Meta.Tau(kind=0, suba=204, subb=122), @Meta.Tau(kind=0, suba=205, subb=124),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=207, subb=22), @Meta.Tau(kind=0, suba=208, subb=23), @Meta.Tau(kind=0, suba=209, subb=53),
    @Meta.Tau(kind=0, suba=210, subb=54), @Meta.Tau(kind=0, suba=211, subb=24), @Meta.Tau(kind=0, suba=212, subb=55),
    @Meta.Tau(kind=0, suba=213, subb=56), @Meta.Tau(kind=0, suba=214, subb=57), @Meta.Tau(kind=0, suba=215, subb=18),
    @Meta.Tau(kind=0, suba=216, subb=101), @Meta.Tau(kind=0, suba=217, subb=103), @Meta.Tau(kind=0, suba=218, subb=105),
    @Meta.Tau(kind=0, suba=219, subb=107), @Meta.Tau(kind=0, suba=220, subb=12), @Meta.Tau(kind=0, suba=221, subb=16),
    @Meta.Tau(kind=0, suba=222, subb=111), @Meta.Tau(kind=0, suba=223, subb=113), @Meta.Tau(kind=0, suba=224, subb=10),
    @Meta.Tau(kind=0, suba=225, subb=62), @Meta.Tau(kind=0, suba=226, subb=117),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=228, subb=22), @Meta.Tau(kind=0, suba=229, subb=23), @Meta.Tau(kind=0, suba=230, subb=53),
    @Meta.Tau(kind=0, suba=231, subb=54), @Meta.Tau(kind=0, suba=232, subb=24), @Meta.Tau(kind=0, suba=233, subb=55),
    @Meta.Tau(kind=0, suba=234, subb=56), @Meta.Tau(kind=0, suba=235, subb=57), @Meta.Tau(kind=0, suba=236, subb=18),
    @Meta.Tau(kind=0, suba=237, subb=101), @Meta.Tau(kind=0, suba=238, subb=103), @Meta.Tau(kind=0, suba=239, subb=105),
    @Meta.Tau(kind=0, suba=240, subb=107), @Meta.Tau(kind=0, suba=241, subb=12), @Meta.Tau(kind=0, suba=242, subb=16),
    @Meta.Tau(kind=0, suba=243, subb=111), @Meta.Tau(kind=0, suba=244, subb=113), @Meta.Tau(kind=0, suba=245, subb=10),
    @Meta.Tau(kind=0, suba=246, subb=62), @Meta.Tau(kind=0, suba=247, subb=117), @Meta.Tau(kind=0, suba=248, subb=71),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=250, subb=22), @Meta.Tau(kind=0, suba=251, subb=23), @Meta.Tau(kind=0, suba=252, subb=53),
    @Meta.Tau(kind=0, suba=253, subb=54), @Meta.Tau(kind=0, suba=254, subb=24), @Meta.Tau(kind=0, suba=255, subb=55),
    @Meta.Tau(kind=0, suba=256, subb=56), @Meta.Tau(kind=0, suba=257, subb=57), @Meta.Tau(kind=0, suba=258, subb=18),
    @Meta.Tau(kind=0, suba=259, subb=101), @Meta.Tau(kind=0, suba=260, subb=103), @Meta.Tau(kind=0, suba=261, subb=105),
    @Meta.Tau(kind=0, suba=262, subb=107), @Meta.Tau(kind=0, suba=263, subb=12), @Meta.Tau(kind=0, suba=264, subb=16),
    @Meta.Tau(kind=0, suba=265, subb=111), @Meta.Tau(kind=0, suba=266, subb=113), @Meta.Tau(kind=0, suba=267, subb=10),
    @Meta.Tau(kind=0, suba=268, subb=62), @Meta.Tau(kind=0, suba=269, subb=117), @Meta.Tau(kind=0, suba=270, subb=71),
    @Meta.Tau(kind=0, suba=271, subb=120),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=273, subb=22), @Meta.Tau(kind=0, suba=274, subb=23), @Meta.Tau(kind=0, suba=275, subb=53),
    @Meta.Tau(kind=0, suba=276, subb=54), @Meta.Tau(kind=0, suba=277, subb=24), @Meta.Tau(kind=0, suba=278, subb=55),
    @Meta.Tau(kind=0, suba=279, subb=56), @Meta.Tau(kind=0, suba=280, subb=57), @Meta.Tau(kind=0, suba=281, subb=18),
    @Meta.Tau(kind=0, suba=282, subb=101), @Meta.Tau(kind=0, suba=283, subb=103), @Meta.Tau(kind=0, suba=284, subb=105),
    @Meta.Tau(kind=0, suba=285, subb=107), @Meta.Tau(kind=0, suba=286, subb=12), @Meta.Tau(kind=0, suba=287, subb=16),
    @Meta.Tau(kind=0, suba=288, subb=111), @Meta.Tau(kind=0, suba=289, subb=113),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=291, subb=22), @Meta.Tau(kind=0, suba=292, subb=23), @Meta.Tau(kind=0, suba=293, subb=53),
    @Meta.Tau(kind=0, suba=294, subb=54), @Meta.Tau(kind=0, suba=295, subb=24), @Meta.Tau(kind=0, suba=296, subb=55),
    @Meta.Tau(kind=0, suba=297, subb=56), @Meta.Tau(kind=0, suba=298, subb=57), @Meta.Tau(kind=0, suba=299, subb=18),
    @Meta.Tau(kind=0, suba=300, subb=101), @Meta.Tau(kind=0, suba=301, subb=103), @Meta.Tau(kind=0, suba=302, subb=105),
    @Meta.Tau(kind=0, suba=303, subb=107), @Meta.Tau(kind=0, suba=304, subb=12), @Meta.Tau(kind=0, suba=305, subb=16),
    @Meta.Tau(kind=0, suba=306, subb=111), @Meta.Tau(kind=0, suba=307, subb=113), @Meta.Tau(kind=0, suba=308, subb=10),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=310, subb=22), @Meta.Tau(kind=0, suba=311, subb=23), @Meta.Tau(kind=0, suba=312, subb=53),
    @Meta.Tau(kind=0, suba=313, subb=54), @Meta.Tau(kind=0, suba=314, subb=24), @Meta.Tau(kind=0, suba=315, subb=55),
    @Meta.Tau(kind=0, suba=316, subb=56), @Meta.Tau(kind=0, suba=317, subb=57), @Meta.Tau(kind=0, suba=318, subb=18),
    @Meta.Tau(kind=0, suba=319, subb=101), @Meta.Tau(kind=0, suba=320, subb=103), @Meta.Tau(kind=0, suba=321, subb=105),
    @Meta.Tau(kind=0, suba=322, subb=107), @Meta.Tau(kind=0, suba=323, subb=12),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=325, subb=22), @Meta.Tau(kind=0, suba=326, subb=23), @Meta.Tau(kind=0, suba=327, subb=53),
    @Meta.Tau(kind=0, suba=328, subb=54), @Meta.Tau(kind=0, suba=329, subb=24), @Meta.Tau(kind=0, suba=330, subb=55),
    @Meta.Tau(kind=0, suba=331, subb=56), @Meta.Tau(kind=0, suba=332, subb=57), @Meta.Tau(kind=0, suba=333, subb=18),
    @Meta.Tau(kind=0, suba=334, subb=101), @Meta.Tau(kind=0, suba=335, subb=103), @Meta.Tau(kind=0, suba=336, subb=105),
    @Meta.Tau(kind=0, suba=337, subb=107), @Meta.Tau(kind=0, suba=338, subb=12), @Meta.Tau(kind=0, suba=339, subb=16),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=341, subb=22), @Meta.Tau(kind=0, suba=342, subb=23), @Meta.Tau(kind=0, suba=343, subb=53),
    @Meta.Tau(kind=0, suba=344, subb=54), @Meta.Tau(kind=0, suba=345, subb=24), @Meta.Tau(kind=0, suba=346, subb=55),
    @Meta.Tau(kind=0, suba=347, subb=56), @Meta.Tau(kind=0, suba=348, subb=57), @Meta.Tau(kind=0, suba=349, subb=18),
    @Meta.Tau(kind=0, suba=350, subb=101), @Meta.Tau(kind=0, suba=351, subb=103), @Meta.Tau(kind=0, suba=352, subb=105),
    @Meta.Tau(kind=0, suba=353, subb=107), @Meta.Tau(kind=0, suba=354, subb=12), @Meta.Tau(kind=0, suba=355, subb=16),
    @Meta.Tau(kind=0, suba=356, subb=111),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=358, subb=22), @Meta.Tau(kind=0, suba=359, subb=23), @Meta.Tau(kind=0, suba=360, subb=53),
    @Meta.Tau(kind=0, suba=361, subb=54), @Meta.Tau(kind=0, suba=362, subb=24), @Meta.Tau(kind=0, suba=363, subb=55),
    @Meta.Tau(kind=0, suba=364, subb=56), @Meta.Tau(kind=0, suba=365, subb=57), @Meta.Tau(kind=0, suba=366, subb=18),
    @Meta.Tau(kind=0, suba=367, subb=101), @Meta.Tau(kind=0, suba=368, subb=103), @Meta.Tau(kind=0, suba=369, subb=105),
    @Meta.Tau(kind=0, suba=370, subb=107), @Meta.Tau(kind=0, suba=371, subb=12), @Meta.Tau(kind=0, suba=372, subb=16),
    @Meta.Tau(kind=0, suba=373, subb=111), @Meta.Tau(kind=0, suba=374, subb=113), @Meta.Tau(kind=0, suba=375, subb=10),
    @Meta.Tau(kind=0, suba=376, subb=62),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=378, subb=22), @Meta.Tau(kind=0, suba=379, subb=23), @Meta.Tau(kind=0, suba=380, subb=53),
    @Meta.Tau(kind=0, suba=381, subb=54), @Meta.Tau(kind=0, suba=382, subb=24), @Meta.Tau(kind=0, suba=383, subb=55),
    @Meta.Tau(kind=0, suba=384, subb=56), @Meta.Tau(kind=0, suba=385, subb=57), @Meta.Tau(kind=0, suba=386, subb=18),
    @Meta.Tau(kind=0, suba=387, subb=101), @Meta.Tau(kind=0, suba=388, subb=103),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=390, subb=22), @Meta.Tau(kind=0, suba=391, subb=23), @Meta.Tau(kind=0, suba=392, subb=53),
    @Meta.Tau(kind=0, suba=393, subb=54), @Meta.Tau(kind=0, suba=394, subb=24), @Meta.Tau(kind=0, suba=395, subb=55),
    @Meta.Tau(kind=0, suba=396, subb=56), @Meta.Tau(kind=0, suba=397, subb=57), @Meta.Tau(kind=0, suba=398, subb=18),
    @Meta.Tau(kind=0, suba=399, subb=101), @Meta.Tau(kind=0, suba=400, subb=103), @Meta.Tau(kind=0, suba=401, subb=105),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=403, subb=22), @Meta.Tau(kind=0, suba=404, subb=23), @Meta.Tau(kind=0, suba=405, subb=53),
    @Meta.Tau(kind=0, suba=406, subb=54), @Meta.Tau(kind=0, suba=407, subb=24), @Meta.Tau(kind=0, suba=408, subb=55),
    @Meta.Tau(kind=0, suba=409, subb=56), @Meta.Tau(kind=0, suba=410, subb=57),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=412, subb=22), @Meta.Tau(kind=0, suba=413, subb=23), @Meta.Tau(kind=0, suba=414, subb=53),
    @Meta.Tau(kind=0, suba=415, subb=54), @Meta.Tau(kind=0, suba=416, subb=24), @Meta.Tau(kind=0, suba=417, subb=55),
    @Meta.Tau(kind=0, suba=418, subb=56), @Meta.Tau(kind=0, suba=419, subb=57), @Meta.Tau(kind=0, suba=420, subb=18),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=422, subb=22), @Meta.Tau(kind=0, suba=423, subb=23), @Meta.Tau(kind=0, suba=424, subb=53),
    @Meta.Tau(kind=0, suba=425, subb=54), @Meta.Tau(kind=0, suba=426, subb=24), @Meta.Tau(kind=0, suba=427, subb=55),
    @Meta.Tau(kind=0, suba=428, subb=56), @Meta.Tau(kind=0, suba=429, subb=57), @Meta.Tau(kind=0, suba=430, subb=18),
    @Meta.Tau(kind=0, suba=431, subb=101),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,)")}),
    @Meta.Tau(kind=0, suba=433, subb=22), @Meta.Tau(kind=0, suba=434, subb=23), @Meta.Tau(kind=0, suba=435, subb=53),
    @Meta.Tau(kind=0, suba=436, subb=54), @Meta.Tau(kind=0, suba=437, subb=24),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,)")}),
    @Meta.Tau(kind=0, suba=439, subb=22), @Meta.Tau(kind=0, suba=440, subb=23), @Meta.Tau(kind=0, suba=441, subb=53),
    @Meta.Tau(kind=0, suba=442, subb=54), @Meta.Tau(kind=0, suba=443, subb=24), @Meta.Tau(kind=0, suba=444, subb=55),
    @Meta.Tau(kind=0, suba=19, subb=22), @Meta.Tau(kind=0, suba=446, subb=23),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,)")}),
    @Meta.Tau(kind=0, suba=448, subb=22), @Meta.Tau(kind=0, suba=449, subb=23), @Meta.Tau(kind=0, suba=450, subb=53),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,)")}),
    @Meta.Tau(kind=0, suba=452, subb=22), @Meta.Tau(kind=0, suba=453, subb=23), @Meta.Tau(kind=0, suba=454, subb=53),
    @Meta.Tau(kind=0, suba=455, subb=54),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,)")}),
    @Meta.Tau(kind=0, suba=457, subb=22), @Meta.Tau(kind=0, suba=458, subb=23), @Meta.Tau(kind=0, suba=459, subb=53),
    @Meta.Tau(kind=0, suba=460, subb=54), @Meta.Tau(kind=0, suba=461, subb=24), @Meta.Tau(kind=0, suba=462, subb=55),
    @Meta.Tau(kind=0, suba=463, subb=56),
    @Meta.Tau(kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,)")}),
    @Meta.Tau(kind=0, suba=465, subb=22), @Meta.Tau(kind=0, suba=466, subb=23), @Meta.Tau(kind=0, suba=467, subb=53),
    @Meta.Tau(kind=0, suba=468, subb=54), @Meta.Tau(kind=0, suba=469, subb=24), @Meta.Tau(kind=0, suba=470, subb=55),
    @Meta.Tau(kind=0, suba=471, subb=56), @Meta.Tau(kind=0, suba=472, subb=57), @Meta.Tau(kind=0, suba=473, subb=18),
    @Meta.Tau(kind=0, suba=474, subb=101), @Meta.Tau(kind=0, suba=475, subb=103), @Meta.Tau(kind=0, suba=476, subb=105),
    @Meta.Tau(kind=0, suba=477, subb=107),
    @Meta.Tau(
      kind=2, suba=0, tcon={@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="(,,,,,,,,,,,,,,,,,,,,,,,,)")}
    ),
    @Meta.Tau(kind=0, suba=479, subb=22), @Meta.Tau(kind=0, suba=480, subb=23), @Meta.Tau(kind=0, suba=481, subb=53),
    @Meta.Tau(kind=0, suba=482, subb=54), @Meta.Tau(kind=0, suba=483, subb=24), @Meta.Tau(kind=0, suba=484, subb=55),
    @Meta.Tau(kind=0, suba=485, subb=56), @Meta.Tau(kind=0, suba=486, subb=57), @Meta.Tau(kind=0, suba=487, subb=18),
    @Meta.Tau(kind=0, suba=488, subb=101), @Meta.Tau(kind=0, suba=489, subb=103), @Meta.Tau(kind=0, suba=490, subb=105),
    @Meta.Tau(kind=0, suba=491, subb=107), @Meta.Tau(kind=0, suba=492, subb=12), @Meta.Tau(kind=0, suba=493, subb=16),
    @Meta.Tau(kind=0, suba=494, subb=111), @Meta.Tau(kind=0, suba=495, subb=113), @Meta.Tau(kind=0, suba=496, subb=10),
    @Meta.Tau(kind=0, suba=497, subb=62), @Meta.Tau(kind=0, suba=498, subb=117), @Meta.Tau(kind=0, suba=499, subb=71),
    @Meta.Tau(kind=0, suba=500, subb=120), @Meta.Tau(kind=0, suba=501, subb=122), @Meta.Tau(kind=0, suba=502, subb=124),
    @Meta.Tau(kind=0, suba=503, subb=126), @Meta.Tau(kind=0, suba=154, subb=31), @Meta.Tau(kind=0, suba=505, subb=65),
    @Meta.Tau(kind=0, suba=154, subb=65), @Meta.Tau(suba=3, tvar="γ"), @Meta.Tau(kind=0, suba=154, subb=508),
    @Meta.Tau(kind=0, suba=509, subb=31), @Meta.Tau(kind=0, suba=507, subb=510), @Meta.Tau(kind=0, suba=19, subb=65),
    @Meta.Tau(kind=0, suba=512, subb=508), @Meta.Tau(kind=0, suba=512, subb=31), @Meta.Tau(kind=0, suba=507, subb=31),
    @Meta.Tau(kind=0, suba=507, subb=515), @Meta.Tau(kind=0, suba=509, subb=65), @Meta.Tau(kind=0, suba=80, subb=65),
    @Meta.Tau(kind=0, suba=19, subb=31), @Meta.Tau(kind=0, suba=519, subb=65), @Meta.Tau(kind=0, suba=7, subb=65),
    @Meta.Tau(kind=0, suba=521, subb=508), @Meta.Tau(kind=0, suba=154, subb=513), @Meta.Tau(kind=0, suba=523, subb=31)
  },
  rhos={
    @Meta.Rho(rhofun=false, rhotau=2), @Meta.Rho(rhofun=false, rhotau=6), @Meta.Rho(rhofun=false, rhotau=9),
    @Meta.Rho(
      rhofun=false,
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), tau=10)}, rhotau=10
    ),
    @Meta.Rho(rhofun=false, rhotau=10), @Meta.Rho(sigma=4, rhotau=4),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), tau=10)}, sigma=4,
      rhotau=5
    ),
    @Meta.Rho(rhofun=false, rhotau=11),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Real"), tau=10)}, sigma=6,
      rhotau=4
    ),
    @Meta.Rho(
      rhofun=false,
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, rhotau=12
    ),
    @Meta.Rho(rhofun=false, rhotau=12), @Meta.Rho(sigma=9, rhotau=10),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=9,
      rhotau=11
    ),
    @Meta.Rho(rhofun=false, rhotau=13),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=9,
      rhotau=13
    ),
    @Meta.Rho(rhofun=false, rhotau=14),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=9,
      rhotau=15
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=9,
      rhotau=10
    ),
    @Meta.Rho(rhofun=false, rhotau=15),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=14,
      rhotau=10
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=12)}, sigma=16,
      rhotau=10
    ),
    @Meta.Rho(rhofun=false, rhotau=16), @Meta.Rho(sigma=18, rhotau=21),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), tau=16)}, sigma=18,
      rhotau=22
    ),
    @Meta.Rho(rhofun=false, rhotau=17), @Meta.Rho(sigma=18, rhotau=24),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), tau=16)}, sigma=18,
      rhotau=25
    ),
    @Meta.Rho(sigma=18, rhotau=15),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), tau=16)}, sigma=18,
      rhotau=27
    ),
    @Meta.Rho(rhofun=false, rhotau=18), @Meta.Rho(sigma=22, rhotau=29),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18)},
      sigma=22, rhotau=30
    ),
    @Meta.Rho(rhofun=false, rhotau=21), @Meta.Rho(sigma=22, rhotau=32),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18)},
      sigma=22, rhotau=33
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18)},
      sigma=22, rhotau=15
    ),
    @Meta.Rho(rhofun=false, rhotau=22), @Meta.Rho(sigma=22, rhotau=36),
    @Meta.Rho(
      cont={
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18),
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=22)
      },
      sigma=26, rhotau=37
    ),
    @Meta.Rho(sigma=26, rhotau=36), @Meta.Rho(sigma=22, rhotau=39),
    @Meta.Rho(
      cont={
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18),
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=22)
      },
      sigma=26, rhotau=40
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18)},
      sigma=22, rhotau=18
    ),
    @Meta.Rho(rhofun=false, rhotau=23),
    @Meta.Rho(
      cont={
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Integral"), tau=18),
        @Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=23)
      },
      sigma=22, rhotau=43
    ),
    @Meta.Rho(rhofun=false, rhotau=24),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=45
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=13
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=16,
      rhotau=45
    ),
    @Meta.Rho(rhofun=false, rhotau=26), @Meta.Rho(sigma=31, rhotau=49),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=50
    ),
    @Meta.Rho(sigma=31, rhotau=50),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=52
    ),
    @Meta.Rho(sigma=31, rhotau=24),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=54
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Enum"), tau=24)}, sigma=31,
      rhotau=49
    ),
    @Meta.Rho(
      rhofun=false,
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Bounded"), tau=23)},
      rhotau=23
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=24)}, sigma=31,
      rhotau=13
    ),
    @Meta.Rho(sigma=31, rhotau=15),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=24)}, sigma=31,
      rhotau=59
    ),
    @Meta.Rho(sigma=6, rhotau=13), @Meta.Rho(sigma=6, rhotau=7), @Meta.Rho(sigma=6, rhotau=62),
    @Meta.Rho(sigma=6, rhotau=15), @Meta.Rho(sigma=16, rhotau=7), @Meta.Rho(sigma=14, rhotau=7),
    @Meta.Rho(rhofun=false, rhotau=27), @Meta.Rho(sigma=48, rhotau=13), @Meta.Rho(sigma=48, rhotau=67),
    @Meta.Rho(sigma=48, rhotau=69), @Meta.Rho(sigma=48, rhotau=15), @Meta.Rho(sigma=16, rhotau=67),
    @Meta.Rho(sigma=6, rhotau=67), @Meta.Rho(sigma=14, rhotau=67), @Meta.Rho(rhofun=false, rhotau=28),
    @Meta.Rho(sigma=56, rhotau=75), @Meta.Rho(sigma=56, rhotau=76), @Meta.Rho(sigma=56, rhotau=15),
    @Meta.Rho(sigma=56, rhotau=78), @Meta.Rho(sigma=56, rhotau=24), @Meta.Rho(sigma=56, rhotau=80),
    @Meta.Rho(sigma=0, rhotau=0), @Meta.Rho(sigma=0, rhotau=82), @Meta.Rho(sigma=0, rhotau=24),
    @Meta.Rho(sigma=0, rhotau=84), @Meta.Rho(sigma=0, rhotau=15), @Meta.Rho(sigma=0, rhotau=86),
    @Meta.Rho(sigma=16, rhotau=13), @Meta.Rho(sigma=16, rhotau=88), @Meta.Rho(sigma=16, rhotau=15),
    @Meta.Rho(sigma=16, rhotau=90), @Meta.Rho(sigma=16, rhotau=24), @Meta.Rho(sigma=16, rhotau=92),
    @Meta.Rho(sigma=6, rhotau=64), @Meta.Rho(sigma=6, rhotau=24), @Meta.Rho(sigma=6, rhotau=95),
    @Meta.Rho(sigma=48, rhotau=71), @Meta.Rho(sigma=48, rhotau=24), @Meta.Rho(sigma=48, rhotau=98),
    @Meta.Rho(sigma=70, rhotau=15), @Meta.Rho(sigma=70, rhotau=100), @Meta.Rho(sigma=70, rhotau=24),
    @Meta.Rho(sigma=70, rhotau=102), @Meta.Rho(rhofun=false, rhotau=1), @Meta.Rho(sigma=73, rhotau=104),
    @Meta.Rho(sigma=73, rhotau=105), @Meta.Rho(sigma=73, rhotau=24), @Meta.Rho(sigma=73, rhotau=107),
    @Meta.Rho(sigma=73, rhotau=15), @Meta.Rho(sigma=73, rhotau=109), @Meta.Rho(sigma=14, rhotau=18),
    @Meta.Rho(sigma=14, rhotau=111), @Meta.Rho(sigma=14, rhotau=13), @Meta.Rho(sigma=14, rhotau=15),
    @Meta.Rho(sigma=14, rhotau=114), @Meta.Rho(sigma=14, rhotau=24), @Meta.Rho(sigma=14, rhotau=116),
    @Meta.Rho(sigma=56, rhotau=13), @Meta.Rho(sigma=16, rhotau=75), @Meta.Rho(sigma=14, rhotau=75),
    @Meta.Rho(rhofun=false, rhotau=30), @Meta.Rho(sigma=56, rhotau=121), @Meta.Rho(sigma=56, rhotau=122),
    @Meta.Rho(rhofun=false, rhotau=31), @Meta.Rho(sigma=89, rhotau=124), @Meta.Rho(sigma=56, rhotau=125),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=126
    ),
    @Meta.Rho(sigma=56, rhotau=124),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=128
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=56,
      rhotau=124
    ),
    @Meta.Rho(sigma=56, rhotau=18), @Meta.Rho(rhofun=false, rhotau=33), @Meta.Rho(sigma=16, rhotau=132),
    @Meta.Rho(sigma=16, rhotau=133), @Meta.Rho(sigma=16, rhotau=125),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=135
    ),
    @Meta.Rho(sigma=16, rhotau=124),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=137
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=16,
      rhotau=124
    ),
    @Meta.Rho(sigma=16, rhotau=18), @Meta.Rho(rhofun=false, rhotau=35), @Meta.Rho(sigma=14, rhotau=141),
    @Meta.Rho(sigma=14, rhotau=142), @Meta.Rho(sigma=14, rhotau=125),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=144
    ),
    @Meta.Rho(sigma=14, rhotau=124),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=89,
      rhotau=146
    ),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Num"), tau=31)}, sigma=14,
      rhotau=124
    ),
    @Meta.Rho(sigma=0, rhotau=13),
    @Meta.Rho(
      rhofun=false, cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=22)},
      rhotau=36
    ),
    @Meta.Rho(rhofun=false, rhotau=37),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=31)}, sigma=107,
      rhotau=13
    ),
    @Meta.Rho(sigma=107, rhotau=15),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=31)}, sigma=107,
      rhotau=153
    ),
    @Meta.Rho(rhofun=false, rhotau=38), @Meta.Rho(sigma=110, rhotau=13), @Meta.Rho(sigma=110, rhotau=15),
    @Meta.Rho(sigma=110, rhotau=157), @Meta.Rho(sigma=70, rhotau=13), @Meta.Rho(sigma=73, rhotau=13),
    @Meta.Rho(rhofun=false, rhotau=39), @Meta.Rho(sigma=56, rhotau=161), @Meta.Rho(sigma=56, rhotau=162),
    @Meta.Rho(sigma=56, rhotau=163), @Meta.Rho(rhofun=false, rhotau=40), @Meta.Rho(sigma=14, rhotau=165),
    @Meta.Rho(sigma=14, rhotau=166), @Meta.Rho(sigma=14, rhotau=167), @Meta.Rho(sigma=16, rhotau=104),
    @Meta.Rho(rhofun=false, rhotau=41), @Meta.Rho(sigma=73, rhotau=170), @Meta.Rho(sigma=73, rhotau=171),
    @Meta.Rho(sigma=73, rhotau=172), @Meta.Rho(rhofun=false, rhotau=42), @Meta.Rho(sigma=70, rhotau=174),
    @Meta.Rho(sigma=70, rhotau=175), @Meta.Rho(sigma=70, rhotau=176), @Meta.Rho(rhofun=false, rhotau=43),
    @Meta.Rho(sigma=16, rhotau=178), @Meta.Rho(sigma=16, rhotau=179), @Meta.Rho(sigma=16, rhotau=180),
    @Meta.Rho(rhofun=false, rhotau=44), @Meta.Rho(rhofun=false, rhotau=8), @Meta.Rho(sigma=134, rhotau=182),
    @Meta.Rho(sigma=0, rhotau=184), @Meta.Rho(sigma=0, rhotau=182), @Meta.Rho(sigma=133, rhotau=15),
    @Meta.Rho(rhofun=false, rhotau=45), @Meta.Rho(rhofun=false, rhotau=46), @Meta.Rho(rhofun=false, rhotau=47),
    @Meta.Rho(sigma=0, rhotau=190), @Meta.Rho(sigma=73, rhotau=0), @Meta.Rho(sigma=73, rhotau=192),
    @Meta.Rho(sigma=0, rhotau=193), @Meta.Rho(sigma=16, rhotau=189), @Meta.Rho(sigma=16, rhotau=195),
    @Meta.Rho(sigma=144, rhotau=196), @Meta.Rho(rhofun=false, rhotau=51), @Meta.Rho(sigma=0, rhotau=198),
    @Meta.Rho(sigma=0, rhotau=88), @Meta.Rho(sigma=0, rhotau=200), @Meta.Rho(sigma=73, rhotau=88),
    @Meta.Rho(sigma=0, rhotau=202), @Meta.Rho(sigma=0, rhotau=149), @Meta.Rho(sigma=144, rhotau=13),
    @Meta.Rho(sigma=16, rhotau=0), @Meta.Rho(rhofun=false, rhotau=52), @Meta.Rho(sigma=0, rhotau=207),
    @Meta.Rho(sigma=0, rhotau=160), @Meta.Rho(sigma=16, rhotau=36), @Meta.Rho(sigma=144, rhotau=210),
    @Meta.Rho(rhofun=false, rhotau=53), @Meta.Rho(rhofun=false, rhotau=54), @Meta.Rho(rhofun=false, rhotau=55),
    @Meta.Rho(rhofun=false, rhotau=56), @Meta.Rho(rhofun=false, rhotau=57), @Meta.Rho(sigma=22, rhotau=0),
    @Meta.Rho(sigma=161, rhotau=217), @Meta.Rho(sigma=160, rhotau=218), @Meta.Rho(sigma=159, rhotau=219),
    @Meta.Rho(sigma=31, rhotau=220), @Meta.Rho(sigma=158, rhotau=221), @Meta.Rho(sigma=157, rhotau=222),
    @Meta.Rho(sigma=156, rhotau=223), @Meta.Rho(sigma=26, rhotau=224), @Meta.Rho(sigma=0, rhotau=225),
    @Meta.Rho(sigma=160, rhotau=0), @Meta.Rho(sigma=159, rhotau=227), @Meta.Rho(sigma=31, rhotau=228),
    @Meta.Rho(sigma=158, rhotau=229), @Meta.Rho(sigma=157, rhotau=230), @Meta.Rho(sigma=156, rhotau=231),
    @Meta.Rho(sigma=26, rhotau=232), @Meta.Rho(sigma=0, rhotau=233), @Meta.Rho(sigma=161, rhotau=0),
    @Meta.Rho(sigma=160, rhotau=235), @Meta.Rho(sigma=159, rhotau=236), @Meta.Rho(sigma=31, rhotau=237),
    @Meta.Rho(sigma=158, rhotau=238), @Meta.Rho(sigma=157, rhotau=239), @Meta.Rho(sigma=156, rhotau=240),
    @Meta.Rho(sigma=26, rhotau=241), @Meta.Rho(sigma=0, rhotau=242), @Meta.Rho(sigma=31, rhotau=0),
    @Meta.Rho(sigma=158, rhotau=244), @Meta.Rho(sigma=157, rhotau=245), @Meta.Rho(sigma=156, rhotau=246),
    @Meta.Rho(sigma=26, rhotau=247), @Meta.Rho(sigma=0, rhotau=248), @Meta.Rho(sigma=157, rhotau=0),
    @Meta.Rho(sigma=156, rhotau=250), @Meta.Rho(sigma=26, rhotau=251), @Meta.Rho(sigma=0, rhotau=252),
    @Meta.Rho(sigma=158, rhotau=0), @Meta.Rho(sigma=157, rhotau=254), @Meta.Rho(sigma=156, rhotau=255),
    @Meta.Rho(sigma=26, rhotau=256), @Meta.Rho(sigma=0, rhotau=257), @Meta.Rho(sigma=26, rhotau=0),
    @Meta.Rho(sigma=0, rhotau=259), @Meta.Rho(rhofun=false, rhotau=58), @Meta.Rho(sigma=0, rhotau=261),
    @Meta.Rho(sigma=156, rhotau=0), @Meta.Rho(sigma=26, rhotau=263), @Meta.Rho(sigma=0, rhotau=264),
    @Meta.Rho(sigma=159, rhotau=0), @Meta.Rho(sigma=31, rhotau=266), @Meta.Rho(sigma=158, rhotau=267),
    @Meta.Rho(sigma=157, rhotau=268), @Meta.Rho(sigma=156, rhotau=269), @Meta.Rho(sigma=26, rhotau=270),
    @Meta.Rho(sigma=0, rhotau=271), @Meta.Rho(sigma=0, rhotau=89), @Meta.Rho(sigma=0, rhotau=18),
    @Meta.Rho(sigma=0, rhotau=169), @Meta.Rho(sigma=0, rhotau=7), @Meta.Rho(sigma=0, rhotau=67),
    @Meta.Rho(sigma=144, rhotau=189), @Meta.Rho(sigma=144, rhotau=278), @Meta.Rho(sigma=0, rhotau=75),
    @Meta.Rho(rhofun=false, rhotau=59), @Meta.Rho(sigma=0, rhotau=281), @Meta.Rho(rhofun=false, rhotau=60),
    @Meta.Rho(sigma=0, rhotau=283), @Meta.Rho(sigma=134, rhotau=190), @Meta.Rho(sigma=134, rhotau=0),
    @Meta.Rho(rhofun=false, rhotau=64), @Meta.Rho(rhofun=false, rhotau=62), @Meta.Rho(sigma=185, rhotau=36),
    @Meta.Rho(sigma=186, rhotau=287), @Meta.Rho(rhofun=false, rhotau=65), @Meta.Rho(rhofun=false, rhotau=67),
    @Meta.Rho(sigma=188, rhotau=292), @Meta.Rho(rhofun=false, rhotau=70), @Meta.Rho(sigma=190, rhotau=36),
    @Meta.Rho(rhofun=false, rhotau=73), @Meta.Rho(rhofun=false, rhotau=74), @Meta.Rho(sigma=26, rhotau=297),
    @Meta.Rho(sigma=193, rhotau=297), @Meta.Rho(sigma=192, rhotau=299), @Meta.Rho(rhofun=false, rhotau=75),
    @Meta.Rho(sigma=195, rhotau=36), @Meta.Rho(rhofun=false, rhotau=5), @Meta.Rho(rhofun=false, rhotau=77),
    @Meta.Rho(sigma=199, rhotau=190), @Meta.Rho(rhofun=false, rhotau=79), @Meta.Rho(sigma=26, rhotau=306),
    @Meta.Rho(rhofun=false, rhotau=49), @Meta.Rho(sigma=134, rhotau=308), @Meta.Rho(sigma=0, rhotau=309),
    @Meta.Rho(sigma=0, rhotau=308), @Meta.Rho(rhofun=false, rhotau=81), @Meta.Rho(sigma=26, rhotau=312),
    @Meta.Rho(rhofun=false, rhotau=82), @Meta.Rho(rhofun=false, rhotau=48), @Meta.Rho(sigma=14, rhotau=0),
    @Meta.Rho(sigma=14, rhotau=140), @Meta.Rho(sigma=14, rhotau=113), @Meta.Rho(rhofun=false, rhotau=83),
    @Meta.Rho(sigma=48, rhotau=7), @Meta.Rho(rhofun=false, rhotau=84), @Meta.Rho(sigma=6, rhotau=75),
    @Meta.Rho(rhofun=false, rhotau=88), @Meta.Rho(sigma=0, rhotau=323), @Meta.Rho(sigma=218, rhotau=0),
    @Meta.Rho(rhofun=false, rhotau=85), @Meta.Rho(rhofun=false, rhotau=90), @Meta.Rho(sigma=156, rhotau=327),
    @Meta.Rho(sigma=26, rhotau=327), @Meta.Rho(rhofun=false, rhotau=129), @Meta.Rho(rhofun=false, rhotau=101),
    @Meta.Rho(rhofun=false, rhotau=103), @Meta.Rho(rhofun=false, rhotau=105), @Meta.Rho(rhofun=false, rhotau=107),
    @Meta.Rho(rhofun=false, rhotau=111), @Meta.Rho(rhofun=false, rhotau=113), @Meta.Rho(rhofun=false, rhotau=117),
    @Meta.Rho(rhofun=false, rhotau=71), @Meta.Rho(rhofun=false, rhotau=120), @Meta.Rho(rhofun=false, rhotau=122),
    @Meta.Rho(rhofun=false, rhotau=124), @Meta.Rho(rhofun=false, rhotau=126), @Meta.Rho(rhofun=false, rhotau=128),
    @Meta.Rho(sigma=237, rhotau=330), @Meta.Rho(sigma=236, rhotau=344), @Meta.Rho(sigma=235, rhotau=345),
    @Meta.Rho(sigma=234, rhotau=346), @Meta.Rho(sigma=233, rhotau=347), @Meta.Rho(sigma=232, rhotau=348),
    @Meta.Rho(sigma=231, rhotau=349), @Meta.Rho(sigma=185, rhotau=350), @Meta.Rho(sigma=4, rhotau=351),
    @Meta.Rho(sigma=230, rhotau=352), @Meta.Rho(sigma=229, rhotau=353), @Meta.Rho(sigma=18, rhotau=354),
    @Meta.Rho(sigma=9, rhotau=355), @Meta.Rho(sigma=228, rhotau=356), @Meta.Rho(sigma=227, rhotau=357),
    @Meta.Rho(sigma=226, rhotau=358), @Meta.Rho(sigma=225, rhotau=359), @Meta.Rho(sigma=22, rhotau=360),
    @Meta.Rho(sigma=161, rhotau=361), @Meta.Rho(sigma=160, rhotau=362), @Meta.Rho(sigma=159, rhotau=363),
    @Meta.Rho(sigma=31, rhotau=364), @Meta.Rho(sigma=158, rhotau=365), @Meta.Rho(sigma=157, rhotau=366),
    @Meta.Rho(sigma=156, rhotau=367), @Meta.Rho(sigma=26, rhotau=368), @Meta.Rho(rhofun=false, rhotau=156),
    @Meta.Rho(sigma=73, rhotau=160), @Meta.Rho(rhofun=false, rhotau=157), @Meta.Rho(sigma=16, rhotau=372),
    @Meta.Rho(sigma=73, rhotau=372), @Meta.Rho(sigma=16, rhotau=169), @Meta.Rho(sigma=56, rhotau=119),
    @Meta.Rho(sigma=56, rhotau=7), @Meta.Rho(sigma=56, rhotau=67), @Meta.Rho(rhofun=false, rhotau=181),
    @Meta.Rho(sigma=234, rhotau=379), @Meta.Rho(sigma=233, rhotau=380), @Meta.Rho(sigma=232, rhotau=381),
    @Meta.Rho(sigma=231, rhotau=382), @Meta.Rho(sigma=185, rhotau=383), @Meta.Rho(sigma=4, rhotau=384),
    @Meta.Rho(sigma=230, rhotau=385), @Meta.Rho(sigma=229, rhotau=386), @Meta.Rho(sigma=18, rhotau=387),
    @Meta.Rho(sigma=9, rhotau=388), @Meta.Rho(sigma=228, rhotau=389), @Meta.Rho(sigma=227, rhotau=390),
    @Meta.Rho(sigma=226, rhotau=391), @Meta.Rho(sigma=225, rhotau=392), @Meta.Rho(sigma=22, rhotau=393),
    @Meta.Rho(sigma=161, rhotau=394), @Meta.Rho(sigma=160, rhotau=395), @Meta.Rho(sigma=159, rhotau=396),
    @Meta.Rho(sigma=31, rhotau=397), @Meta.Rho(sigma=158, rhotau=398), @Meta.Rho(sigma=157, rhotau=399),
    @Meta.Rho(sigma=156, rhotau=400), @Meta.Rho(sigma=26, rhotau=401), @Meta.Rho(rhofun=false, rhotau=206),
    @Meta.Rho(sigma=235, rhotau=403), @Meta.Rho(sigma=234, rhotau=404), @Meta.Rho(sigma=233, rhotau=405),
    @Meta.Rho(sigma=232, rhotau=406), @Meta.Rho(sigma=231, rhotau=407), @Meta.Rho(sigma=185, rhotau=408),
    @Meta.Rho(sigma=4, rhotau=409), @Meta.Rho(sigma=230, rhotau=410), @Meta.Rho(sigma=229, rhotau=411),
    @Meta.Rho(sigma=18, rhotau=412), @Meta.Rho(sigma=9, rhotau=413), @Meta.Rho(sigma=228, rhotau=414),
    @Meta.Rho(sigma=227, rhotau=415), @Meta.Rho(sigma=226, rhotau=416), @Meta.Rho(sigma=225, rhotau=417),
    @Meta.Rho(sigma=22, rhotau=418), @Meta.Rho(sigma=161, rhotau=419), @Meta.Rho(sigma=160, rhotau=420),
    @Meta.Rho(sigma=159, rhotau=421), @Meta.Rho(sigma=31, rhotau=422), @Meta.Rho(sigma=158, rhotau=423),
    @Meta.Rho(sigma=157, rhotau=424), @Meta.Rho(sigma=156, rhotau=425), @Meta.Rho(sigma=26, rhotau=426),
    @Meta.Rho(rhofun=false, rhotau=227), @Meta.Rho(sigma=231, rhotau=428), @Meta.Rho(sigma=185, rhotau=429),
    @Meta.Rho(sigma=4, rhotau=430), @Meta.Rho(sigma=230, rhotau=431), @Meta.Rho(sigma=229, rhotau=432),
    @Meta.Rho(sigma=18, rhotau=433), @Meta.Rho(sigma=9, rhotau=434), @Meta.Rho(sigma=228, rhotau=435),
    @Meta.Rho(sigma=227, rhotau=436), @Meta.Rho(sigma=226, rhotau=437), @Meta.Rho(sigma=225, rhotau=438),
    @Meta.Rho(sigma=22, rhotau=439), @Meta.Rho(sigma=161, rhotau=440), @Meta.Rho(sigma=160, rhotau=441),
    @Meta.Rho(sigma=159, rhotau=442), @Meta.Rho(sigma=31, rhotau=443), @Meta.Rho(sigma=158, rhotau=444),
    @Meta.Rho(sigma=157, rhotau=445), @Meta.Rho(sigma=156, rhotau=446), @Meta.Rho(sigma=26, rhotau=447),
    @Meta.Rho(rhofun=false, rhotau=249), @Meta.Rho(sigma=232, rhotau=449), @Meta.Rho(sigma=231, rhotau=450),
    @Meta.Rho(sigma=185, rhotau=451), @Meta.Rho(sigma=4, rhotau=452), @Meta.Rho(sigma=230, rhotau=453),
    @Meta.Rho(sigma=229, rhotau=454), @Meta.Rho(sigma=18, rhotau=455), @Meta.Rho(sigma=9, rhotau=456),
    @Meta.Rho(sigma=228, rhotau=457), @Meta.Rho(sigma=227, rhotau=458), @Meta.Rho(sigma=226, rhotau=459),
    @Meta.Rho(sigma=225, rhotau=460), @Meta.Rho(sigma=22, rhotau=461), @Meta.Rho(sigma=161, rhotau=462),
    @Meta.Rho(sigma=160, rhotau=463), @Meta.Rho(sigma=159, rhotau=464), @Meta.Rho(sigma=31, rhotau=465),
    @Meta.Rho(sigma=158, rhotau=466), @Meta.Rho(sigma=157, rhotau=467), @Meta.Rho(sigma=156, rhotau=468),
    @Meta.Rho(sigma=26, rhotau=469), @Meta.Rho(rhofun=false, rhotau=272), @Meta.Rho(sigma=233, rhotau=471),
    @Meta.Rho(sigma=232, rhotau=472), @Meta.Rho(sigma=231, rhotau=473), @Meta.Rho(sigma=185, rhotau=474),
    @Meta.Rho(sigma=4, rhotau=475), @Meta.Rho(sigma=230, rhotau=476), @Meta.Rho(sigma=229, rhotau=477),
    @Meta.Rho(sigma=18, rhotau=478), @Meta.Rho(sigma=9, rhotau=479), @Meta.Rho(sigma=228, rhotau=480),
    @Meta.Rho(sigma=227, rhotau=481), @Meta.Rho(sigma=226, rhotau=482), @Meta.Rho(sigma=225, rhotau=483),
    @Meta.Rho(sigma=22, rhotau=484), @Meta.Rho(sigma=161, rhotau=485), @Meta.Rho(sigma=160, rhotau=486),
    @Meta.Rho(sigma=159, rhotau=487), @Meta.Rho(sigma=31, rhotau=488), @Meta.Rho(sigma=158, rhotau=489),
    @Meta.Rho(sigma=157, rhotau=490), @Meta.Rho(sigma=156, rhotau=491), @Meta.Rho(sigma=26, rhotau=492),
    @Meta.Rho(rhofun=false, rhotau=290), @Meta.Rho(sigma=230, rhotau=494), @Meta.Rho(sigma=229, rhotau=495),
    @Meta.Rho(sigma=18, rhotau=496), @Meta.Rho(sigma=9, rhotau=497), @Meta.Rho(sigma=228, rhotau=498),
    @Meta.Rho(sigma=227, rhotau=499), @Meta.Rho(sigma=226, rhotau=500), @Meta.Rho(sigma=225, rhotau=501),
    @Meta.Rho(sigma=22, rhotau=502), @Meta.Rho(sigma=161, rhotau=503), @Meta.Rho(sigma=160, rhotau=504),
    @Meta.Rho(sigma=159, rhotau=505), @Meta.Rho(sigma=31, rhotau=506), @Meta.Rho(sigma=158, rhotau=507),
    @Meta.Rho(sigma=157, rhotau=508), @Meta.Rho(sigma=156, rhotau=509), @Meta.Rho(sigma=26, rhotau=510),
    @Meta.Rho(rhofun=false, rhotau=309), @Meta.Rho(sigma=4, rhotau=512), @Meta.Rho(sigma=230, rhotau=513),
    @Meta.Rho(sigma=229, rhotau=514), @Meta.Rho(sigma=18, rhotau=515), @Meta.Rho(sigma=9, rhotau=516),
    @Meta.Rho(sigma=228, rhotau=517), @Meta.Rho(sigma=227, rhotau=518), @Meta.Rho(sigma=226, rhotau=519),
    @Meta.Rho(sigma=225, rhotau=520), @Meta.Rho(sigma=22, rhotau=521), @Meta.Rho(sigma=161, rhotau=522),
    @Meta.Rho(sigma=160, rhotau=523), @Meta.Rho(sigma=159, rhotau=524), @Meta.Rho(sigma=31, rhotau=525),
    @Meta.Rho(sigma=158, rhotau=526), @Meta.Rho(sigma=157, rhotau=527), @Meta.Rho(sigma=156, rhotau=528),
    @Meta.Rho(sigma=26, rhotau=529), @Meta.Rho(rhofun=false, rhotau=324), @Meta.Rho(sigma=9, rhotau=531),
    @Meta.Rho(sigma=228, rhotau=532), @Meta.Rho(sigma=227, rhotau=533), @Meta.Rho(sigma=226, rhotau=534),
    @Meta.Rho(sigma=225, rhotau=535), @Meta.Rho(sigma=22, rhotau=536), @Meta.Rho(sigma=161, rhotau=537),
    @Meta.Rho(sigma=160, rhotau=538), @Meta.Rho(sigma=159, rhotau=539), @Meta.Rho(sigma=31, rhotau=540),
    @Meta.Rho(sigma=158, rhotau=541), @Meta.Rho(sigma=157, rhotau=542), @Meta.Rho(sigma=156, rhotau=543),
    @Meta.Rho(sigma=26, rhotau=544), @Meta.Rho(rhofun=false, rhotau=340), @Meta.Rho(sigma=18, rhotau=546),
    @Meta.Rho(sigma=9, rhotau=547), @Meta.Rho(sigma=228, rhotau=548), @Meta.Rho(sigma=227, rhotau=549),
    @Meta.Rho(sigma=226, rhotau=550), @Meta.Rho(sigma=225, rhotau=551), @Meta.Rho(sigma=22, rhotau=552),
    @Meta.Rho(sigma=161, rhotau=553), @Meta.Rho(sigma=160, rhotau=554), @Meta.Rho(sigma=159, rhotau=555),
    @Meta.Rho(sigma=31, rhotau=556), @Meta.Rho(sigma=158, rhotau=557), @Meta.Rho(sigma=157, rhotau=558),
    @Meta.Rho(sigma=156, rhotau=559), @Meta.Rho(sigma=26, rhotau=560), @Meta.Rho(rhofun=false, rhotau=357),
    @Meta.Rho(sigma=229, rhotau=562), @Meta.Rho(sigma=18, rhotau=563), @Meta.Rho(sigma=9, rhotau=564),
    @Meta.Rho(sigma=228, rhotau=565), @Meta.Rho(sigma=227, rhotau=566), @Meta.Rho(sigma=226, rhotau=567),
    @Meta.Rho(sigma=225, rhotau=568), @Meta.Rho(sigma=22, rhotau=569), @Meta.Rho(sigma=161, rhotau=570),
    @Meta.Rho(sigma=160, rhotau=571), @Meta.Rho(sigma=159, rhotau=572), @Meta.Rho(sigma=31, rhotau=573),
    @Meta.Rho(sigma=158, rhotau=574), @Meta.Rho(sigma=157, rhotau=575), @Meta.Rho(sigma=156, rhotau=576),
    @Meta.Rho(sigma=26, rhotau=577), @Meta.Rho(rhofun=false, rhotau=377), @Meta.Rho(sigma=185, rhotau=579),
    @Meta.Rho(sigma=4, rhotau=580), @Meta.Rho(sigma=230, rhotau=581), @Meta.Rho(sigma=229, rhotau=582),
    @Meta.Rho(sigma=18, rhotau=583), @Meta.Rho(sigma=9, rhotau=584), @Meta.Rho(sigma=228, rhotau=585),
    @Meta.Rho(sigma=227, rhotau=586), @Meta.Rho(sigma=226, rhotau=587), @Meta.Rho(sigma=225, rhotau=588),
    @Meta.Rho(sigma=22, rhotau=589), @Meta.Rho(sigma=161, rhotau=590), @Meta.Rho(sigma=160, rhotau=591),
    @Meta.Rho(sigma=159, rhotau=592), @Meta.Rho(sigma=31, rhotau=593), @Meta.Rho(sigma=158, rhotau=594),
    @Meta.Rho(sigma=157, rhotau=595), @Meta.Rho(sigma=156, rhotau=596), @Meta.Rho(sigma=26, rhotau=597),
    @Meta.Rho(rhofun=false, rhotau=389), @Meta.Rho(sigma=226, rhotau=599), @Meta.Rho(sigma=225, rhotau=600),
    @Meta.Rho(sigma=22, rhotau=601), @Meta.Rho(sigma=161, rhotau=602), @Meta.Rho(sigma=160, rhotau=603),
    @Meta.Rho(sigma=159, rhotau=604), @Meta.Rho(sigma=31, rhotau=605), @Meta.Rho(sigma=158, rhotau=606),
    @Meta.Rho(sigma=157, rhotau=607), @Meta.Rho(sigma=156, rhotau=608), @Meta.Rho(sigma=26, rhotau=609),
    @Meta.Rho(rhofun=false, rhotau=402), @Meta.Rho(sigma=227, rhotau=611), @Meta.Rho(sigma=226, rhotau=612),
    @Meta.Rho(sigma=225, rhotau=613), @Meta.Rho(sigma=22, rhotau=614), @Meta.Rho(sigma=161, rhotau=615),
    @Meta.Rho(sigma=160, rhotau=616), @Meta.Rho(sigma=159, rhotau=617), @Meta.Rho(sigma=31, rhotau=618),
    @Meta.Rho(sigma=158, rhotau=619), @Meta.Rho(sigma=157, rhotau=620), @Meta.Rho(sigma=156, rhotau=621),
    @Meta.Rho(sigma=26, rhotau=622), @Meta.Rho(rhofun=false, rhotau=411), @Meta.Rho(sigma=161, rhotau=624),
    @Meta.Rho(sigma=160, rhotau=625), @Meta.Rho(sigma=159, rhotau=626), @Meta.Rho(sigma=31, rhotau=627),
    @Meta.Rho(sigma=158, rhotau=628), @Meta.Rho(sigma=157, rhotau=629), @Meta.Rho(sigma=156, rhotau=630),
    @Meta.Rho(sigma=26, rhotau=631), @Meta.Rho(rhofun=false, rhotau=421), @Meta.Rho(sigma=22, rhotau=633),
    @Meta.Rho(sigma=161, rhotau=634), @Meta.Rho(sigma=160, rhotau=635), @Meta.Rho(sigma=159, rhotau=636),
    @Meta.Rho(sigma=31, rhotau=637), @Meta.Rho(sigma=158, rhotau=638), @Meta.Rho(sigma=157, rhotau=639),
    @Meta.Rho(sigma=156, rhotau=640), @Meta.Rho(sigma=26, rhotau=641), @Meta.Rho(rhofun=false, rhotau=432),
    @Meta.Rho(sigma=225, rhotau=643), @Meta.Rho(sigma=22, rhotau=644), @Meta.Rho(sigma=161, rhotau=645),
    @Meta.Rho(sigma=160, rhotau=646), @Meta.Rho(sigma=159, rhotau=647), @Meta.Rho(sigma=31, rhotau=648),
    @Meta.Rho(sigma=158, rhotau=649), @Meta.Rho(sigma=157, rhotau=650), @Meta.Rho(sigma=156, rhotau=651),
    @Meta.Rho(sigma=26, rhotau=652), @Meta.Rho(rhofun=false, rhotau=438), @Meta.Rho(sigma=31, rhotau=654),
    @Meta.Rho(sigma=158, rhotau=655), @Meta.Rho(sigma=157, rhotau=656), @Meta.Rho(sigma=156, rhotau=657),
    @Meta.Rho(sigma=26, rhotau=658), @Meta.Rho(rhofun=false, rhotau=445), @Meta.Rho(sigma=159, rhotau=660),
    @Meta.Rho(sigma=31, rhotau=661), @Meta.Rho(sigma=158, rhotau=662), @Meta.Rho(sigma=157, rhotau=663),
    @Meta.Rho(sigma=156, rhotau=664), @Meta.Rho(sigma=26, rhotau=665), @Meta.Rho(rhofun=false, rhotau=447),
    @Meta.Rho(sigma=156, rhotau=667), @Meta.Rho(sigma=26, rhotau=668), @Meta.Rho(rhofun=false, rhotau=451),
    @Meta.Rho(sigma=157, rhotau=670), @Meta.Rho(sigma=156, rhotau=671), @Meta.Rho(sigma=26, rhotau=672),
    @Meta.Rho(rhofun=false, rhotau=456), @Meta.Rho(sigma=158, rhotau=674), @Meta.Rho(sigma=157, rhotau=675),
    @Meta.Rho(sigma=156, rhotau=676), @Meta.Rho(sigma=26, rhotau=677), @Meta.Rho(rhofun=false, rhotau=464),
    @Meta.Rho(sigma=160, rhotau=679), @Meta.Rho(sigma=159, rhotau=680), @Meta.Rho(sigma=31, rhotau=681),
    @Meta.Rho(sigma=158, rhotau=682), @Meta.Rho(sigma=157, rhotau=683), @Meta.Rho(sigma=156, rhotau=684),
    @Meta.Rho(sigma=26, rhotau=685), @Meta.Rho(rhofun=false, rhotau=478), @Meta.Rho(sigma=228, rhotau=687),
    @Meta.Rho(sigma=227, rhotau=688), @Meta.Rho(sigma=226, rhotau=689), @Meta.Rho(sigma=225, rhotau=690),
    @Meta.Rho(sigma=22, rhotau=691), @Meta.Rho(sigma=161, rhotau=692), @Meta.Rho(sigma=160, rhotau=693),
    @Meta.Rho(sigma=159, rhotau=694), @Meta.Rho(sigma=31, rhotau=695), @Meta.Rho(sigma=158, rhotau=696),
    @Meta.Rho(sigma=157, rhotau=697), @Meta.Rho(sigma=156, rhotau=698), @Meta.Rho(sigma=26, rhotau=699),
    @Meta.Rho(rhofun=false, rhotau=504), @Meta.Rho(sigma=236, rhotau=701), @Meta.Rho(sigma=235, rhotau=702),
    @Meta.Rho(sigma=234, rhotau=703), @Meta.Rho(sigma=233, rhotau=704), @Meta.Rho(sigma=232, rhotau=705),
    @Meta.Rho(sigma=231, rhotau=706), @Meta.Rho(sigma=185, rhotau=707), @Meta.Rho(sigma=4, rhotau=708),
    @Meta.Rho(sigma=230, rhotau=709), @Meta.Rho(sigma=229, rhotau=710), @Meta.Rho(sigma=18, rhotau=711),
    @Meta.Rho(sigma=9, rhotau=712), @Meta.Rho(sigma=228, rhotau=713), @Meta.Rho(sigma=227, rhotau=714),
    @Meta.Rho(sigma=226, rhotau=715), @Meta.Rho(sigma=225, rhotau=716), @Meta.Rho(sigma=22, rhotau=717),
    @Meta.Rho(sigma=161, rhotau=718), @Meta.Rho(sigma=160, rhotau=719), @Meta.Rho(sigma=159, rhotau=720),
    @Meta.Rho(sigma=31, rhotau=721), @Meta.Rho(sigma=158, rhotau=722), @Meta.Rho(sigma=157, rhotau=723),
    @Meta.Rho(sigma=156, rhotau=724), @Meta.Rho(sigma=26, rhotau=725), @Meta.Rho(rhofun=false, rhotau=36),
    @Meta.Rho(sigma=298, rhotau=727), @Meta.Rho(sigma=26, rhotau=728), @Meta.Rho(rhofun=false, rhotau=506),
    @Meta.Rho(sigma=89, rhotau=15), @Meta.Rho(sigma=89, rhotau=731),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Eq"), tau=65)}, sigma=300,
      rhotau=732
    ),
    @Meta.Rho(sigma=134, rhotau=124), @Meta.Rho(rhofun=false, rhotau=511), @Meta.Rho(rhofun=false, rhotau=513),
    @Meta.Rho(sigma=305, rhotau=124), @Meta.Rho(sigma=304, rhotau=737), @Meta.Rho(sigma=144, rhotau=195),
    @Meta.Rho(rhofun=false, rhotau=514), @Meta.Rho(sigma=330, rhotau=124), @Meta.Rho(sigma=188, rhotau=291),
    @Meta.Rho(sigma=89, rhotau=742), @Meta.Rho(rhofun=false, rhotau=516), @Meta.Rho(rhofun=false, rhotau=517),
    @Meta.Rho(rhofun=false, rhotau=508), @Meta.Rho(sigma=337, rhotau=124), @Meta.Rho(sigma=337, rhotau=747),
    @Meta.Rho(sigma=336, rhotau=748), @Meta.Rho(sigma=335, rhotau=749), @Meta.Rho(rhofun=false, rhotau=515),
    @Meta.Rho(rhofun=false, rhotau=518), @Meta.Rho(sigma=340, rhotau=124), @Meta.Rho(sigma=339, rhotau=753),
    @Meta.Rho(sigma=89, rhotau=754), @Meta.Rho(rhofun=false, rhotau=520), @Meta.Rho(sigma=344, rhotau=124),
    @Meta.Rho(sigma=188, rhotau=124), @Meta.Rho(sigma=337, rhotau=758), @Meta.Rho(sigma=304, rhotau=759),
    @Meta.Rho(sigma=0, rhotau=338), @Meta.Rho(rhofun=false, rhotau=510), @Meta.Rho(rhofun=false, rhotau=522),
    @Meta.Rho(sigma=349, rhotau=124), @Meta.Rho(sigma=348, rhotau=764), @Meta.Rho(sigma=339, rhotau=765),
    @Meta.Rho(sigma=89, rhotau=24), @Meta.Rho(sigma=89, rhotau=767),
    @Meta.Rho(
      cont={@Meta.Context(clas=@Meta.QName(kind=0, pack="frege.prelude.PreludeBase", base="Ord"), tau=65)}, sigma=300,
      rhotau=768
    ),
    @Meta.Rho(rhofun=false, rhotau=524), @Meta.Rho(sigma=188, rhotau=747), @Meta.Rho(sigma=353, rhotau=771),
    @Meta.Rho(sigma=89, rhotau=291), @Meta.Rho(sigma=188, rhotau=773), @Meta.Rho(sigma=70, rhotau=124),
    @Meta.Rho(sigma=89, rhotau=775), @Meta.Rho(sigma=89, rhotau=776), @Meta.Rho(sigma=26, rhotau=39),
    @Meta.Rho(sigma=26, rhotau=13), @Meta.Rho(sigma=89, rhotau=125), @Meta.Rho(sigma=70, rhotau=780),
    @Meta.Rho(sigma=188, rhotau=155), @Meta.Rho(sigma=89, rhotau=782), @Meta.Rho(sigma=26, rhotau=15),
    @Meta.Rho(sigma=26, rhotau=784), @Meta.Rho(sigma=339, rhotau=758), @Meta.Rho(sigma=107, rhotau=151),
    @Meta.Rho(sigma=89, rhotau=787)
  },
  sigmas={
    @Meta.Sigma(rho=0), @Meta.Sigma(rho=1), @Meta.Sigma(rho=2), @Meta.Sigma(bound={"r"}, kinds={3}, rho=3),
    @Meta.Sigma(rho=4), @Meta.Sigma(bound={"r"}, kinds={3}, rho=6), @Meta.Sigma(rho=7),
    @Meta.Sigma(bound={"r"}, kinds={3}, rho=8), @Meta.Sigma(bound={"n"}, kinds={3}, rho=9), @Meta.Sigma(rho=10),
    @Meta.Sigma(bound={"n"}, kinds={3}, rho=12), @Meta.Sigma(bound={"n"}, kinds={3}, rho=14),
    @Meta.Sigma(bound={"n"}, kinds={3}, rho=16), @Meta.Sigma(bound={"n"}, kinds={3}, rho=17), @Meta.Sigma(rho=18),
    @Meta.Sigma(bound={"n"}, kinds={3}, rho=19), @Meta.Sigma(rho=13), @Meta.Sigma(bound={"n"}, kinds={3}, rho=20),
    @Meta.Sigma(rho=21), @Meta.Sigma(bound={"o"}, kinds={3}, rho=23), @Meta.Sigma(bound={"o"}, kinds={3}, rho=26),
    @Meta.Sigma(bound={"o"}, kinds={3}, rho=28), @Meta.Sigma(rho=29), @Meta.Sigma(bound={"i"}, kinds={3}, rho=31),
    @Meta.Sigma(bound={"i"}, kinds={3}, rho=34), @Meta.Sigma(bound={"i"}, kinds={3}, rho=35), @Meta.Sigma(rho=36),
    @Meta.Sigma(bound={"i", "a"}, kinds={3, 3}, rho=38), @Meta.Sigma(bound={"i", "a"}, kinds={3, 3}, rho=41),
    @Meta.Sigma(bound={"i"}, kinds={3}, rho=42), @Meta.Sigma(bound={"i", "b"}, kinds={3, 3}, rho=44),
    @Meta.Sigma(rho=45), @Meta.Sigma(bound={"e"}, kinds={3}, rho=46), @Meta.Sigma(bound={"e"}, kinds={3}, rho=47),
    @Meta.Sigma(bound={"e"}, kinds={3}, rho=48), @Meta.Sigma(bound={"e"}, kinds={3}, rho=51),
    @Meta.Sigma(bound={"e"}, kinds={3}, rho=53), @Meta.Sigma(bound={"e"}, kinds={3}, rho=55),
    @Meta.Sigma(bound={"e"}, kinds={3}, rho=56), @Meta.Sigma(bound={"b"}, kinds={3}, rho=57),
    @Meta.Sigma(bound={"e"}, kinds={3}, rho=58), @Meta.Sigma(bound={"e"}, kinds={3}, rho=60), @Meta.Sigma(rho=61),
    @Meta.Sigma(rho=63), @Meta.Sigma(rho=62), @Meta.Sigma(rho=64), @Meta.Sigma(rho=65), @Meta.Sigma(rho=66),
    @Meta.Sigma(rho=67), @Meta.Sigma(rho=68), @Meta.Sigma(rho=70), @Meta.Sigma(rho=69), @Meta.Sigma(rho=71),
    @Meta.Sigma(rho=72), @Meta.Sigma(rho=73), @Meta.Sigma(rho=74), @Meta.Sigma(rho=75), @Meta.Sigma(rho=77),
    @Meta.Sigma(rho=79), @Meta.Sigma(rho=81), @Meta.Sigma(rho=83), @Meta.Sigma(rho=85), @Meta.Sigma(rho=87),
    @Meta.Sigma(rho=89), @Meta.Sigma(rho=91), @Meta.Sigma(rho=93), @Meta.Sigma(rho=94), @Meta.Sigma(rho=96),
    @Meta.Sigma(rho=97), @Meta.Sigma(rho=99), @Meta.Sigma(rho=15), @Meta.Sigma(rho=101), @Meta.Sigma(rho=103),
    @Meta.Sigma(rho=104), @Meta.Sigma(rho=106), @Meta.Sigma(rho=108), @Meta.Sigma(rho=110), @Meta.Sigma(rho=112),
    @Meta.Sigma(rho=113), @Meta.Sigma(rho=115), @Meta.Sigma(rho=117), @Meta.Sigma(rho=118), @Meta.Sigma(rho=78),
    @Meta.Sigma(rho=76), @Meta.Sigma(rho=119), @Meta.Sigma(rho=120), @Meta.Sigma(rho=88), @Meta.Sigma(rho=90),
    @Meta.Sigma(rho=123), @Meta.Sigma(rho=124), @Meta.Sigma(bound={"α"}, kinds={3}, rho=127),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=129), @Meta.Sigma(bound={"α"}, kinds={3}, rho=130), @Meta.Sigma(rho=131),
    @Meta.Sigma(rho=134), @Meta.Sigma(bound={"α"}, kinds={3}, rho=136), @Meta.Sigma(bound={"α"}, kinds={3}, rho=138),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=139), @Meta.Sigma(rho=140), @Meta.Sigma(rho=143),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=145), @Meta.Sigma(bound={"α"}, kinds={3}, rho=147), @Meta.Sigma(rho=111),
    @Meta.Sigma(rho=114), @Meta.Sigma(bound={"α"}, kinds={3}, rho=148), @Meta.Sigma(rho=149),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=150), @Meta.Sigma(rho=151), @Meta.Sigma(bound={"α"}, kinds={3}, rho=152),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=154), @Meta.Sigma(rho=155), @Meta.Sigma(rho=156), @Meta.Sigma(rho=158),
    @Meta.Sigma(rho=159), @Meta.Sigma(rho=160), @Meta.Sigma(rho=164), @Meta.Sigma(rho=162), @Meta.Sigma(rho=163),
    @Meta.Sigma(rho=168), @Meta.Sigma(rho=167), @Meta.Sigma(rho=166), @Meta.Sigma(rho=105), @Meta.Sigma(rho=169),
    @Meta.Sigma(rho=173), @Meta.Sigma(rho=171), @Meta.Sigma(rho=172), @Meta.Sigma(rho=100), @Meta.Sigma(rho=177),
    @Meta.Sigma(rho=175), @Meta.Sigma(rho=176), @Meta.Sigma(rho=181), @Meta.Sigma(rho=179), @Meta.Sigma(rho=180),
    @Meta.Sigma(rho=182), @Meta.Sigma(rho=183), @Meta.Sigma(rho=185), @Meta.Sigma(rho=184), @Meta.Sigma(rho=186),
    @Meta.Sigma(rho=187), @Meta.Sigma(bound={"ω"}, kinds={3}, rho=188), @Meta.Sigma(bound={"a"}, kinds={3}, rho=189),
    @Meta.Sigma(rho=191), @Meta.Sigma(rho=82), @Meta.Sigma(rho=194), @Meta.Sigma(rho=189),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=197), @Meta.Sigma(rho=199), @Meta.Sigma(rho=201), @Meta.Sigma(rho=203),
    @Meta.Sigma(rho=204), @Meta.Sigma(bound={"a"}, kinds={3}, rho=205), @Meta.Sigma(rho=206), @Meta.Sigma(rho=208),
    @Meta.Sigma(rho=86), @Meta.Sigma(rho=209), @Meta.Sigma(bound={"a"}, kinds={3}, rho=211), @Meta.Sigma(rho=43),
    @Meta.Sigma(rho=212), @Meta.Sigma(rho=213), @Meta.Sigma(rho=214), @Meta.Sigma(rho=215), @Meta.Sigma(rho=216),
    @Meta.Sigma(bound={"i", "g", "h", "e", "c", "a", "b", "d", "f"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=226),
    @Meta.Sigma(bound={"g", "e", "f", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3}, rho=234),
    @Meta.Sigma(bound={"h", "f", "g", "d", "a", "b", "c", "e"}, kinds={3, 3, 3, 3, 3, 3, 3, 3}, rho=243),
    @Meta.Sigma(bound={"e", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3}, rho=249),
    @Meta.Sigma(bound={"c", "a", "b"}, kinds={3, 3, 3}, rho=253),
    @Meta.Sigma(bound={"d", "a", "b", "c"}, kinds={3, 3, 3, 3}, rho=258), @Meta.Sigma(bound={"a"}, kinds={3}, rho=260),
    @Meta.Sigma(rho=262), @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=265),
    @Meta.Sigma(bound={"f", "d", "e", "a", "b", "c"}, kinds={3, 3, 3, 3, 3, 3}, rho=272), @Meta.Sigma(rho=200),
    @Meta.Sigma(rho=273), @Meta.Sigma(rho=274), @Meta.Sigma(rho=275), @Meta.Sigma(rho=276), @Meta.Sigma(rho=277),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=279), @Meta.Sigma(rho=280), @Meta.Sigma(rho=282), @Meta.Sigma(rho=284),
    @Meta.Sigma(rho=285), @Meta.Sigma(rho=286), @Meta.Sigma(bound={"s", "a"}, kinds={3, 3}, rho=287),
    @Meta.Sigma(rho=288), @Meta.Sigma(rho=289), @Meta.Sigma(bound={"s", "a"}, kinds={3, 3}, rho=290),
    @Meta.Sigma(rho=291), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=293),
    @Meta.Sigma(bound={"ß"}, kinds={3}, rho=294), @Meta.Sigma(bound={"a"}, kinds={3}, rho=295), @Meta.Sigma(rho=296),
    @Meta.Sigma(rho=298), @Meta.Sigma(bound={"u", "a", "b"}, kinds={3, 3, 3}, rho=300), @Meta.Sigma(rho=301),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=302), @Meta.Sigma(rho=303), @Meta.Sigma(rho=24), @Meta.Sigma(rho=304),
    @Meta.Sigma(rho=305), @Meta.Sigma(bound={"a"}, kinds={3}, rho=307), @Meta.Sigma(rho=308), @Meta.Sigma(rho=310),
    @Meta.Sigma(rho=311), @Meta.Sigma(bound={"a"}, kinds={3}, rho=312), @Meta.Sigma(bound={"a"}, kinds={3}, rho=313),
    @Meta.Sigma(rho=314), @Meta.Sigma(rho=315), @Meta.Sigma(rho=316), @Meta.Sigma(rho=317), @Meta.Sigma(rho=318),
    @Meta.Sigma(rho=319), @Meta.Sigma(rho=320), @Meta.Sigma(rho=321), @Meta.Sigma(rho=322),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=306), @Meta.Sigma(bound={"a"}, kinds={3}, rho=324), @Meta.Sigma(rho=306),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=325), @Meta.Sigma(rho=326),
    @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=327), @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=328),
    @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=329),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=330
    ),
    @Meta.Sigma(rho=331), @Meta.Sigma(rho=332), @Meta.Sigma(rho=333), @Meta.Sigma(rho=334), @Meta.Sigma(rho=335),
    @Meta.Sigma(rho=336), @Meta.Sigma(rho=337), @Meta.Sigma(rho=338), @Meta.Sigma(rho=339), @Meta.Sigma(rho=340),
    @Meta.Sigma(rho=341), @Meta.Sigma(rho=342), @Meta.Sigma(rho=343),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=369
    ),
    @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=370), @Meta.Sigma(rho=371), @Meta.Sigma(rho=109),
    @Meta.Sigma(rho=373), @Meta.Sigma(rho=374), @Meta.Sigma(rho=202), @Meta.Sigma(rho=375), @Meta.Sigma(rho=376),
    @Meta.Sigma(rho=377), @Meta.Sigma(rho=378),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=379
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=402
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=403
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=427
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=428
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=448
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=449
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=470
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=471
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=493
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=494
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=511
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=512
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=530
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=531
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=545
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=546
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=561
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=562
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=578
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=579
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=598
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=599
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=610
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
      rho=611
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
      rho=623
    ),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g", "h"}, kinds={3, 3, 3, 3, 3, 3, 3, 3}, rho=624),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g", "h"}, kinds={3, 3, 3, 3, 3, 3, 3, 3}, rho=632),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g", "h", "i"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=633),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g", "h", "i"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=642),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=643
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=653
    ),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e"}, kinds={3, 3, 3, 3, 3}, rho=654),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e"}, kinds={3, 3, 3, 3, 3}, rho=659),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f"}, kinds={3, 3, 3, 3, 3, 3}, rho=660),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f"}, kinds={3, 3, 3, 3, 3, 3}, rho=666),
    @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=667), @Meta.Sigma(bound={"a", "b"}, kinds={3, 3}, rho=669),
    @Meta.Sigma(bound={"a", "b", "c"}, kinds={3, 3, 3}, rho=670),
    @Meta.Sigma(bound={"a", "b", "c"}, kinds={3, 3, 3}, rho=673),
    @Meta.Sigma(bound={"a", "b", "c", "d"}, kinds={3, 3, 3, 3}, rho=674),
    @Meta.Sigma(bound={"a", "b", "c", "d"}, kinds={3, 3, 3, 3}, rho=678),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g"}, kinds={3, 3, 3, 3, 3, 3, 3}, rho=679),
    @Meta.Sigma(bound={"a", "b", "c", "d", "e", "f", "g"}, kinds={3, 3, 3, 3, 3, 3, 3}, rho=686),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=687
    ),
    @Meta.Sigma(
      bound={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=700
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=701
    ),
    @Meta.Sigma(
      bound={
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=726
    ),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=727), @Meta.Sigma(rho=727), @Meta.Sigma(bound={"a"}, kinds={3}, rho=729),
    @Meta.Sigma(rho=730), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=733),
    @Meta.Sigma(bound={"u"}, kinds={3}, rho=338), @Meta.Sigma(bound={"α"}, kinds={3}, rho=734), @Meta.Sigma(rho=735),
    @Meta.Sigma(rho=736), @Meta.Sigma(bound={"α", "β", "γ"}, kinds={3, 3, 3}, rho=738),
    @Meta.Sigma(bound={"i", "g", "h", "e", "f", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=642),
    @Meta.Sigma(bound={"h", "g", "e", "f", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3, 3}, rho=632),
    @Meta.Sigma(bound={"e", "c", "d", "a", "b"}, kinds={3, 3, 3, 3, 3}, rho=659),
    @Meta.Sigma(bound={"f", "e", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3}, rho=666),
    @Meta.Sigma(bound={"g", "e", "f", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3}, rho=686),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=739),
    @Meta.Sigma(
      bound={
        "z", "y", "w", "u", "v", "x", "s", "q", "r", "t", "o", "m", "n", "k", "i", "j",
        "l", "g", "e", "f", "c", "a", "b", "d", "h", "p"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=369
    ),
    @Meta.Sigma(bound={"c", "a", "b"}, kinds={3, 3, 3}, rho=673),
    @Meta.Sigma(
      bound={
        "x", "w", "u", "v", "s", "q", "r", "t", "o", "m", "n", "k", "i", "j", "l", "g",
        "e", "f", "c", "a", "b", "d", "h", "p"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=427
    ),
    @Meta.Sigma(
      bound={
        "v", "u", "s", "q", "r", "t", "o", "m", "n", "k", "i", "j", "l", "p", "g", "e",
        "f", "c", "a", "b", "d", "h"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=493
    ),
    @Meta.Sigma(
      bound={
        "w", "u", "v", "s", "q", "r", "t", "o", "m", "n", "k", "i", "j", "l", "p", "g",
        "e", "f", "c", "a", "b", "d", "h"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=402
    ),
    @Meta.Sigma(
      bound={
        "y", "w", "x", "u", "v", "s", "q", "r", "t", "o", "m", "n", "k", "i", "j", "l",
        "g", "e", "f", "c", "a", "b", "d", "h", "p"
      },
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=726
    ),
    @Meta.Sigma(
      bound={"t", "s", "q", "r", "o", "m", "n", "k", "i", "j", "l", "p", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=448
    ),
    @Meta.Sigma(
      bound={"s", "q", "r", "o", "m", "n", "p", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=598
    ),
    @Meta.Sigma(bound={"b", "a"}, kinds={3, 3}, rho=669),
    @Meta.Sigma(
      bound={"u", "s", "t", "q", "r", "o", "m", "n", "k", "i", "j", "l", "p", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=470
    ),
    @Meta.Sigma(bound={"d", "c", "a", "b"}, kinds={3, 3, 3, 3}, rho=678),
    @Meta.Sigma(
      bound={"p", "o", "m", "n", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=578
    ),
    @Meta.Sigma(
      bound={"q", "o", "p", "m", "n", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=511
    ),
    @Meta.Sigma(
      bound={"m", "k", "l", "i", "j", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=700
    ),
    @Meta.Sigma(
      bound={"n", "m", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=545
    ),
    @Meta.Sigma(
      bound={"o", "m", "n", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=561
    ),
    @Meta.Sigma(
      bound={"k", "i", "j", "g", "e", "f", "h", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=610
    ),
    @Meta.Sigma(rho=740), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=741),
    @Meta.Sigma(
      bound={"j", "i", "g", "e", "f", "h", "c", "a", "b", "d"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=653
    ),
    @Meta.Sigma(
      bound={"l", "k", "i", "j", "g", "e", "f", "c", "a", "b", "d", "h"}, kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
      rho=623
    ),
    @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=743), @Meta.Sigma(rho=744), @Meta.Sigma(rho=745),
    @Meta.Sigma(rho=746), @Meta.Sigma(bound={"α", "β", "γ"}, kinds={3, 3, 3}, rho=750), @Meta.Sigma(rho=751),
    @Meta.Sigma(rho=752), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=755),
    @Meta.Sigma(
      bound={"r", "q", "o", "m", "n", "p", "k", "i", "j", "l", "g", "e", "f", "c", "a", "b", "d", "h"},
      kinds={3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, rho=530
    ),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=125), @Meta.Sigma(rho=756),
    @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=757), @Meta.Sigma(bound={"α", "β", "γ"}, kinds={3, 3, 3}, rho=760),
    @Meta.Sigma(bound={"u"}, kinds={3}, rho=761), @Meta.Sigma(rho=762), @Meta.Sigma(rho=763),
    @Meta.Sigma(bound={"α", "β", "γ"}, kinds={3, 3, 3}, rho=766), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=769),
    @Meta.Sigma(rho=192), @Meta.Sigma(rho=770), @Meta.Sigma(bound={"α", "β", "γ"}, kinds={3, 3, 3}, rho=772),
    @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=774), @Meta.Sigma(bound={"α"}, kinds={3}, rho=777),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=778), @Meta.Sigma(bound={"a"}, kinds={3}, rho=779),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=781), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=783),
    @Meta.Sigma(bound={"a"}, kinds={3}, rho=785), @Meta.Sigma(bound={"α", "β"}, kinds={3, 3}, rho=786),
    @Meta.Sigma(bound={"α"}, kinds={3}, rho=788)
  },
  exprs={
    @Meta.Expr(),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="-")}),
    @Meta.Expr(subx1=1950), @Meta.Expr(xkind=1, subx1=1, subx2=2), @Meta.Expr(subx1=1949),
    @Meta.Expr(xkind=1, subx1=3, subx2=4), @Meta.Expr(xkind=5, alts={2, 5}), @Meta.Expr(xkind=5, alts={4, 6}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<=>")}),
    @Meta.Expr(subx1=1951), @Meta.Expr(xkind=1, subx1=8, subx2=9),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="zero")}),
    @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="constructor")}),
    @Meta.Expr(xkind=1, subx1=10, subx2=11),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num_Int", member="-")}),
    @Meta.Expr(xkind=1, subx1=12, subx2=13), @Meta.Expr(xkind=1, subx1=14, subx2=15),
    @Meta.Expr(xkind=6, lkind=3, varval="1"), @Meta.Expr(xkind=1, subx1=16, subx2=17),
    @Meta.Expr(xkind=5, alts={9, 18}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isInfinite")}),
    @Meta.Expr(subx1=1954), @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="!")}),
    @Meta.Expr(xkind=1, subx1=20, subx2=21),
    @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="&&")}),
    @Meta.Expr(xkind=1, subx1=22, subx2=23),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="isNaN")}),
    @Meta.Expr(xkind=1, subx1=26, subx2=21), @Meta.Expr(xkind=1, subx1=24, subx2=25),
    @Meta.Expr(xkind=1, subx1=22, subx2=27), @Meta.Expr(xkind=1, subx1=28, subx2=29),
    @Meta.Expr(xkind=5, alts={21, 30}), @Meta.Expr(subx1=1953), @Meta.Expr(xkind=6, varval="false"),
    @Meta.Expr(xkind=5, alts={32, 33}), @Meta.Expr(xkind=1, subx1=1, subx2=11), @Meta.Expr(subx1=1948),
    @Meta.Expr(xkind=1, subx1=35, subx2=36), @Meta.Expr(xkind=5, alts={36, 37}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member="<")}),
    @Meta.Expr(subx1=1947), @Meta.Expr(xkind=1, subx1=39, subx2=40), @Meta.Expr(xkind=1, subx1=41, subx2=11),
    @Meta.Expr(xkind=1, subx1=35, subx2=40), @Meta.Expr(xkind=4, subx1=42, subx2=43, subx3=40),
    @Meta.Expr(xkind=5, alts={40, 44}), @Meta.Expr(subx1=1952), @Meta.Expr(xkind=5, alts={46, 33}),
    @Meta.Expr(subx1=1932), @Meta.Expr(xkind=1, subx1=39, subx2=48), @Meta.Expr(subx1=1933),
    @Meta.Expr(xkind=1, subx1=49, subx2=50), @Meta.Expr(xkind=4, subx1=51, subx2=48, subx3=50),
    @Meta.Expr(xkind=5, alts={50, 52}), @Meta.Expr(xkind=5, alts={48, 53}), @Meta.Expr(subx1=5131),
    @Meta.Expr(xkind=1, subx1=8, subx2=55), @Meta.Expr(subx1=5132), @Meta.Expr(xkind=1, subx1=56, subx2=57),
    @Meta.Expr(xkind=5, alts={57, 58}), @Meta.Expr(xkind=5, alts={55, 59}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">")}),
    @Meta.Expr(subx1=1930), @Meta.Expr(xkind=1, subx1=61, subx2=62), @Meta.Expr(subx1=1931),
    @Meta.Expr(xkind=1, subx1=63, subx2=64), @Meta.Expr(xkind=4, subx1=65, subx2=62, subx3=64),
    @Meta.Expr(xkind=5, alts={64, 66}), @Meta.Expr(xkind=5, alts={62, 67}), @Meta.Expr(subx1=1927),
    @Meta.Expr(xkind=1, subx1=8, subx2=69), @Meta.Expr(subx1=1928), @Meta.Expr(xkind=1, subx1=70, subx2=71),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Gt")}),
    @Meta.Expr(subx1=1929), @Meta.Expr(xkind=6, varval="true"), @Meta.Expr(xkind=2, alts={73, 74, 75, 33}, subx1=72),
    @Meta.Expr(xkind=5, alts={71, 76}), @Meta.Expr(xkind=5, alts={69, 77}), @Meta.Expr(subx1=1934),
    @Meta.Expr(xkind=1, subx1=8, subx2=79), @Meta.Expr(subx1=1935), @Meta.Expr(xkind=1, subx1=80, subx2=81),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Eq")}),
    @Meta.Expr(subx1=1936), @Meta.Expr(xkind=2, alts={83, 84, 75, 33}, subx1=82), @Meta.Expr(xkind=5, alts={81, 85}),
    @Meta.Expr(xkind=5, alts={79, 86}), @Meta.Expr(subx1=1937), @Meta.Expr(xkind=1, subx1=8, subx2=88),
    @Meta.Expr(subx1=1938), @Meta.Expr(xkind=1, subx1=89, subx2=90), @Meta.Expr(subx1=1939),
    @Meta.Expr(xkind=2, alts={83, 92, 33, 75}, subx1=91), @Meta.Expr(xkind=5, alts={90, 93}),
    @Meta.Expr(xkind=5, alts={88, 94}), @Meta.Expr(subx1=1918), @Meta.Expr(xkind=1, subx1=8, subx2=96),
    @Meta.Expr(subx1=1919), @Meta.Expr(xkind=1, subx1=97, subx2=98),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ordering", member="Lt")}),
    @Meta.Expr(subx1=1920), @Meta.Expr(xkind=2, alts={100, 101, 75, 33}, subx1=99), @Meta.Expr(xkind=5, alts={98, 102}),
    @Meta.Expr(xkind=5, alts={96, 103}), @Meta.Expr(subx1=1921), @Meta.Expr(xkind=1, subx1=8, subx2=105),
    @Meta.Expr(subx1=1922), @Meta.Expr(xkind=1, subx1=106, subx2=107), @Meta.Expr(subx1=1923),
    @Meta.Expr(xkind=2, alts={73, 109, 33, 75}, subx1=108), @Meta.Expr(xkind=5, alts={107, 110}),
    @Meta.Expr(xkind=5, alts={105, 111}), @Meta.Expr(subx1=1924), @Meta.Expr(xkind=1, subx1=8, subx2=113),
    @Meta.Expr(subx1=1925), @Meta.Expr(xkind=1, subx1=114, subx2=115), @Meta.Expr(subx1=1926),
    @Meta.Expr(xkind=2, alts={100, 117, 33, 75}, subx1=116), @Meta.Expr(xkind=5, alts={115, 118}),
    @Meta.Expr(xkind=5, alts={113, 119}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="quot")}),
    @Meta.Expr(subx1=1955), @Meta.Expr(xkind=1, subx1=121, subx2=122), @Meta.Expr(subx1=1956),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="(,)", member="(,)")}),
    @Meta.Expr(xkind=1, subx1=123, subx2=124),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="rem")}),
    @Meta.Expr(xkind=1, subx1=127, subx2=122), @Meta.Expr(xkind=1, subx1=125, subx2=126),
    @Meta.Expr(xkind=1, subx1=128, subx2=124), @Meta.Expr(xkind=1, subx1=129, subx2=130),
    @Meta.Expr(xkind=5, alts={124, 131}), @Meta.Expr(xkind=5, alts={122, 132}), @Meta.Expr(subx1=1979),
    @Meta.Expr(subx1=1980),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="even")}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="*")}),
    @Meta.Expr(xkind=1, subx1=137, subx2=134),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powf")}),
    @Meta.Expr(xkind=1, subx1=138, subx2=134),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInt")}),
    @Meta.Expr(xkind=6, lkind=3, varval="2"), @Meta.Expr(xkind=1, subx1=121, subx2=135),
    @Meta.Expr(xkind=1, subx1=141, subx2=142), @Meta.Expr(xkind=1, subx1=139, subx2=140),
    @Meta.Expr(xkind=1, subx1=143, subx2=144),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq", member="==")}),
    @Meta.Expr(xkind=1, subx1=147, subx2=135), @Meta.Expr(xkind=1, subx1=141, subx2=17),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="powg")}),
    @Meta.Expr(xkind=1, subx1=1, subx2=135), @Meta.Expr(xkind=1, subx1=151, subx2=149),
    @Meta.Expr(xkind=1, subx1=121, subx2=152), @Meta.Expr(xkind=1, subx1=150, subx2=140),
    @Meta.Expr(xkind=1, subx1=153, subx2=144), @Meta.Expr(xkind=1, subx1=154, subx2=155),
    @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="otherwise")}),
    @Meta.Expr(xkind=1, subx1=156, subx2=134), @Meta.Expr(xkind=1, subx1=148, subx2=149), @Meta.Expr(subx1=1982),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 158}, subx1=157), @Meta.Expr(xkind=1, subx1=136, subx2=135),
    @Meta.Expr(subx1=1981), @Meta.Expr(xkind=1, subx1=145, subx2=146),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 160, 134, 161}, subx1=159), @Meta.Expr(xkind=1, subx1=22, subx2=135),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 163, 164, 165}, subx1=162), @Meta.Expr(xkind=1, subx1=22, subx2=134),
    @Meta.Expr(xkind=5, alts={166, 167}), @Meta.Expr(xkind=5, alts={168, 169}), @Meta.Expr(subx1=1983),
    @Meta.Expr(subx1=1984), @Meta.Expr(subx1=1985), @Meta.Expr(xkind=1, subx1=137, subx2=171),
    @Meta.Expr(xkind=1, subx1=174, subx2=171), @Meta.Expr(xkind=1, subx1=121, subx2=172),
    @Meta.Expr(xkind=1, subx1=150, subx2=175), @Meta.Expr(xkind=1, subx1=176, subx2=144),
    @Meta.Expr(xkind=1, subx1=177, subx2=178), @Meta.Expr(xkind=1, subx1=147, subx2=172),
    @Meta.Expr(xkind=1, subx1=1, subx2=172), @Meta.Expr(xkind=1, subx1=181, subx2=149),
    @Meta.Expr(xkind=1, subx1=121, subx2=182), @Meta.Expr(xkind=1, subx1=183, subx2=144),
    @Meta.Expr(xkind=1, subx1=177, subx2=184), @Meta.Expr(xkind=1, subx1=174, subx2=173),
    @Meta.Expr(xkind=1, subx1=185, subx2=186), @Meta.Expr(xkind=1, subx1=180, subx2=149), @Meta.Expr(subx1=1987),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 187}, subx1=157), @Meta.Expr(xkind=1, subx1=136, subx2=172),
    @Meta.Expr(subx1=1986), @Meta.Expr(xkind=1, subx1=179, subx2=173),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 189, 186, 190}, subx1=188), @Meta.Expr(xkind=1, subx1=22, subx2=173),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 192, 193, 194}, subx1=191), @Meta.Expr(xkind=1, subx1=22, subx2=172),
    @Meta.Expr(xkind=5, alts={195, 196}), @Meta.Expr(xkind=1, subx1=22, subx2=171),
    @Meta.Expr(xkind=5, alts={197, 198}), @Meta.Expr(xkind=5, alts={199, 200}), @Meta.Expr(subx1=1972),
    @Meta.Expr(xkind=1, subx1=147, subx2=202),
    @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="||")}),
    @Meta.Expr(xkind=1, subx1=203, subx2=11), @Meta.Expr(subx1=1973), @Meta.Expr(xkind=1, subx1=147, subx2=206),
    @Meta.Expr(xkind=1, subx1=204, subx2=205), @Meta.Expr(xkind=1, subx1=207, subx2=11),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcd")}),
    @Meta.Expr(xkind=1, subx1=210, subx2=202), @Meta.Expr(xkind=1, subx1=121, subx2=202),
    @Meta.Expr(xkind=1, subx1=211, subx2=206), @Meta.Expr(xkind=1, subx1=212, subx2=213),
    @Meta.Expr(xkind=1, subx1=137, subx2=214),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="abs")}),
    @Meta.Expr(xkind=1, subx1=215, subx2=206), @Meta.Expr(xkind=1, subx1=216, subx2=217),
    @Meta.Expr(xkind=1, subx1=208, subx2=209), @Meta.Expr(subx1=1974),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 218}, subx1=157),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 220, 11, 221}, subx1=219), @Meta.Expr(xkind=5, alts={206, 222}),
    @Meta.Expr(xkind=5, alts={202, 223}), @Meta.Expr(subx1=1970),
    @Meta.Expr(
      xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="gcdHelper")}
    ),
    @Meta.Expr(xkind=1, subx1=216, subx2=225), @Meta.Expr(subx1=1971), @Meta.Expr(xkind=1, subx1=226, subx2=227),
    @Meta.Expr(xkind=1, subx1=216, subx2=228), @Meta.Expr(xkind=1, subx1=229, subx2=230),
    @Meta.Expr(xkind=5, alts={228, 231}), @Meta.Expr(xkind=5, alts={225, 232}), @Meta.Expr(subx1=1988),
    @Meta.Expr(subx1=1989), @Meta.Expr(xkind=1, subx1=147, subx2=235), @Meta.Expr(xkind=1, subx1=127, subx2=234),
    @Meta.Expr(xkind=1, subx1=226, subx2=235), @Meta.Expr(xkind=1, subx1=237, subx2=235),
    @Meta.Expr(xkind=1, subx1=238, subx2=239), @Meta.Expr(xkind=1, subx1=236, subx2=11), @Meta.Expr(subx1=1990),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 240}, subx1=157), @Meta.Expr(xkind=1, subx1=22, subx2=235),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 242, 234, 243}, subx1=241), @Meta.Expr(xkind=1, subx1=22, subx2=234),
    @Meta.Expr(xkind=5, alts={244, 245}), @Meta.Expr(xkind=5, alts={246, 247}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="div")}),
    @Meta.Expr(subx1=1957), @Meta.Expr(xkind=1, subx1=249, subx2=250), @Meta.Expr(subx1=1958),
    @Meta.Expr(xkind=1, subx1=251, subx2=252),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="mod")}),
    @Meta.Expr(xkind=1, subx1=254, subx2=250), @Meta.Expr(xkind=1, subx1=125, subx2=253),
    @Meta.Expr(xkind=1, subx1=255, subx2=252), @Meta.Expr(xkind=1, subx1=256, subx2=257),
    @Meta.Expr(xkind=5, alts={252, 258}), @Meta.Expr(xkind=5, alts={250, 259}), @Meta.Expr(subx1=1975),
    @Meta.Expr(subx1=1976), @Meta.Expr(xkind=6, lkind=3, varval="0"), @Meta.Expr(xkind=1, subx1=39, subx2=262),
    @Meta.Expr(xkind=1, subx1=141, subx2=263),
    @Meta.Expr(xkind=8, name={@Meta.QName(pack="frege.prelude.PreludeBase", base="error")}),
    @Meta.Expr(xkind=6, lkind=2, varval="\"Negative exponent\""), @Meta.Expr(xkind=1, subx1=147, subx2=262),
    @Meta.Expr(xkind=1, subx1=139, subx2=261), @Meta.Expr(xkind=1, subx1=269, subx2=262),
    @Meta.Expr(xkind=1, subx1=268, subx2=265), @Meta.Expr(subx1=1978),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 270}, subx1=157), @Meta.Expr(xkind=1, subx1=264, subx2=265),
    @Meta.Expr(subx1=1977), @Meta.Expr(xkind=1, subx1=266, subx2=267),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 272, 149, 273}, subx1=271), @Meta.Expr(xkind=1, subx1=22, subx2=262),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 275, 276, 277}, subx1=274), @Meta.Expr(xkind=1, subx1=22, subx2=261),
    @Meta.Expr(xkind=5, alts={278, 279}), @Meta.Expr(xkind=5, alts={280, 281}), @Meta.Expr(subx1=1964),
    @Meta.Expr(xkind=1, subx1=121, subx2=283), @Meta.Expr(subx1=1965),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord", member=">=")}),
    @Meta.Expr(xkind=1, subx1=286, subx2=283),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Eq_Bool", member="==")}),
    @Meta.Expr(xkind=1, subx1=287, subx2=11), @Meta.Expr(xkind=1, subx1=286, subx2=285),
    @Meta.Expr(xkind=1, subx1=288, subx2=289), @Meta.Expr(xkind=1, subx1=290, subx2=11), @Meta.Expr(subx1=1966),
    @Meta.Expr(xkind=1, subx1=137, subx2=293), @Meta.Expr(xkind=1, subx1=294, subx2=285),
    @Meta.Expr(xkind=1, subx1=147, subx2=295), @Meta.Expr(xkind=1, subx1=1, subx2=293),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="one")}),
    @Meta.Expr(xkind=1, subx1=297, subx2=298), @Meta.Expr(xkind=1, subx1=296, subx2=283), @Meta.Expr(subx1=1968),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 299}, subx1=157), @Meta.Expr(xkind=1, subx1=291, subx2=292),
    @Meta.Expr(subx1=1967), @Meta.Expr(xkind=2, lkind=1, alts={75, 301, 293, 302}, subx1=300),
    @Meta.Expr(xkind=1, subx1=284, subx2=285), @Meta.Expr(xkind=2, lkind=1, alts={75, 304, 293, 305}, subx1=303),
    @Meta.Expr(xkind=2, alts={293, 307}, subx1=306), @Meta.Expr(xkind=5, alts={285, 308}),
    @Meta.Expr(xkind=5, alts={283, 309}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Integral", member="big")}),
    @Meta.Expr(subx1=1969),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="fromInteger")}),
    @Meta.Expr(xkind=1, subx1=311, subx2=312), @Meta.Expr(xkind=1, subx1=313, subx2=314),
    @Meta.Expr(xkind=5, alts={312, 315}), @Meta.Expr(subx1=1959), @Meta.Expr(xkind=1, subx1=127, subx2=317),
    @Meta.Expr(subx1=1960), @Meta.Expr(subx1=1961), @Meta.Expr(xkind=1, subx1=147, subx2=320),
    @Meta.Expr(xkind=1, subx1=286, subx2=317), @Meta.Expr(xkind=1, subx1=322, subx2=11),
    @Meta.Expr(xkind=1, subx1=286, subx2=319), @Meta.Expr(xkind=1, subx1=288, subx2=323),
    @Meta.Expr(xkind=1, subx1=324, subx2=11),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Num", member="+")}),
    @Meta.Expr(xkind=1, subx1=327, subx2=320), @Meta.Expr(xkind=1, subx1=328, subx2=319),
    @Meta.Expr(xkind=1, subx1=325, subx2=326), @Meta.Expr(subx1=1963),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 329}, subx1=157), @Meta.Expr(xkind=1, subx1=321, subx2=11),
    @Meta.Expr(subx1=1962), @Meta.Expr(xkind=2, lkind=1, alts={75, 331, 320, 332}, subx1=330),
    @Meta.Expr(xkind=1, subx1=318, subx2=319), @Meta.Expr(xkind=2, lkind=1, alts={75, 334, 320, 335}, subx1=333),
    @Meta.Expr(xkind=2, alts={320, 337}, subx1=336), @Meta.Expr(xkind=5, alts={319, 338}),
    @Meta.Expr(xkind=5, alts={317, 339}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="ord")}),
    @Meta.Expr(subx1=5133), @Meta.Expr(xkind=1, subx1=341, subx2=342), @Meta.Expr(xkind=5, alts={342, 343}),
    @Meta.Expr(subx1=1940),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Ord_Int", member="<=>")}),
    @Meta.Expr(xkind=1, subx1=341, subx2=345), @Meta.Expr(subx1=1941), @Meta.Expr(xkind=1, subx1=346, subx2=347),
    @Meta.Expr(xkind=1, subx1=341, subx2=348), @Meta.Expr(xkind=1, subx1=349, subx2=350),
    @Meta.Expr(xkind=5, alts={348, 351}), @Meta.Expr(xkind=5, alts={345, 352}),
    @Meta.Expr(
      xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromThen")}
    ),
    @Meta.Expr(subx1=1946),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="succ")}),
    @Meta.Expr(xkind=1, subx1=354, subx2=355), @Meta.Expr(xkind=1, subx1=356, subx2=355),
    @Meta.Expr(xkind=1, subx1=357, subx2=358), @Meta.Expr(xkind=5, alts={355, 359}), @Meta.Expr(subx1=1942),
    @Meta.Expr(xkind=1, subx1=39, subx2=361), @Meta.Expr(subx1=1943),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member=":")}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum", member="enumFromTo")}),
    @Meta.Expr(xkind=1, subx1=356, subx2=361), @Meta.Expr(xkind=1, subx1=365, subx2=366),
    @Meta.Expr(xkind=1, subx1=364, subx2=361), @Meta.Expr(xkind=1, subx1=367, subx2=363),
    @Meta.Expr(xkind=1, subx1=147, subx2=361),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="[]", member="[]")}),
    @Meta.Expr(xkind=1, subx1=370, subx2=363), @Meta.Expr(subx1=1945), @Meta.Expr(xkind=1, subx1=368, subx2=371),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 371}, subx1=157), @Meta.Expr(xkind=1, subx1=362, subx2=363),
    @Meta.Expr(subx1=1944), @Meta.Expr(xkind=1, subx1=368, subx2=369),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 373, 374, 375}, subx1=372),
    @Meta.Expr(xkind=2, lkind=1, alts={75, 377, 378, 379}, subx1=376), @Meta.Expr(xkind=5, alts={363, 380}),
    @Meta.Expr(xkind=5, alts={361, 381}), @Meta.Expr(subx1=1845), @Meta.Expr(xkind=1, subx1=147, subx2=383),
    @Meta.Expr(subx1=1846), @Meta.Expr(xkind=1, subx1=384, subx2=385),
    @Meta.Expr(xkind=4, subx1=386, subx2=33, subx3=75), @Meta.Expr(xkind=5, alts={385, 387}),
    @Meta.Expr(xkind=5, alts={383, 388}), @Meta.Expr(subx1=2241), @Meta.Expr(xkind=5, alts={390, 390}),
    @Meta.Expr(subx1=2105), @Meta.Expr(xkind=5, alts={392, 392}),
    @Meta.Expr(xkind=3, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="ST", member="ST")}),
    @Meta.Expr(subx1=2053), @Meta.Expr(subx1=2055), @Meta.Expr(subx1=2056), @Meta.Expr(subx1=2054),
    @Meta.Expr(subx1=2057), @Meta.Expr(xkind=1, subx1=398, subx2=397), @Meta.Expr(xkind=1, subx1=394, subx2=399),
    @Meta.Expr(xkind=1, subx1=399, subx2=396), @Meta.Expr(xkind=1, subx1=395, subx2=396),
    @Meta.Expr(xkind=1, subx1=22, subx2=397), @Meta.Expr(xkind=2, alts={401, 402}, subx1=400),
    @Meta.Expr(xkind=2, alts={404, 405}, subx1=403), @Meta.Expr(xkind=5, alts={396, 406}),
    @Meta.Expr(xkind=1, subx1=394, subx2=407), @Meta.Expr(xkind=1, subx1=394, subx2=395),
    @Meta.Expr(xkind=5, alts={398, 408}), @Meta.Expr(xkind=5, alts={409, 410}), @Meta.Expr(subx1=1912),
    @Meta.Expr(subx1=1913), @Meta.Expr(xkind=4, subx1=412, subx2=413, subx3=33), @Meta.Expr(xkind=5, alts={413, 414}),
    @Meta.Expr(xkind=5, alts={412, 415}), @Meta.Expr(subx1=1914), @Meta.Expr(subx1=1915),
    @Meta.Expr(xkind=4, subx1=417, subx2=75, subx3=418), @Meta.Expr(xkind=5, alts={418, 419}),
    @Meta.Expr(xkind=5, alts={417, 420}), @Meta.Expr(subx1=2024), @Meta.Expr(subx1=2026),
    @Meta.Expr(xkind=1, subx1=422, subx2=423), @Meta.Expr(subx1=2025), @Meta.Expr(xkind=1, subx1=424, subx2=425),
    @Meta.Expr(xkind=5, alts={423, 426}), @Meta.Expr(xkind=5, alts={425, 427}), @Meta.Expr(xkind=5, alts={422, 428}),
    @Meta.Expr(subx1=2031), @Meta.Expr(xkind=1, subx1=125, subx2=430), @Meta.Expr(subx1=2032), @Meta.Expr(subx1=2030),
    @Meta.Expr(xkind=1, subx1=431, subx2=432), @Meta.Expr(xkind=1, subx1=433, subx2=434),
    @Meta.Expr(xkind=5, alts={432, 435}), @Meta.Expr(xkind=5, alts={430, 436}), @Meta.Expr(xkind=5, alts={433, 437}),
    @Meta.Expr(subx1=2021), @Meta.Expr(subx1=2020), @Meta.Expr(xkind=5, alts={439, 440}),
    @Meta.Expr(xkind=5, alts={440, 441}),
    @Meta.Expr(xkind=8, name={@Meta.QName(kind=2, pack="frege.prelude.PreludeBase", base="Enum_Char", member="from")}),
    @Meta.Expr(subx1=1991), @Meta.Expr(xkind=1, subx1=443, subx2=444), @Meta.Expr(xkind=5, alts={444, 445}),
    @Meta.Expr(subx1=1852), @Meta.Expr(subx1=1850), @Meta.Expr(subx1=1851),
    @Meta.Expr(xkind=4, subx1=447, subx2=448, subx3=449), @Meta.Expr(xkind=5, alts={447, 450}),
    @Meta.Expr(xkind=5, alts={449, 451}), @Meta.Expr(xkind=5, alts={448, 452}), @Meta.Expr(subx1=2023),
    @Meta.Expr(subx1=2022), @Meta.Expr(xkind=5, alts={454, 455}), @Meta.Expr(xkind=5, alts={455, 456}),
    @Meta.Expr(subx1=2019), @Meta.Expr(subx1=2018), @Meta.Expr(xkind=1, subx1=22, subx2=458),
    @Meta.Expr(xkind=1, subx1=459, subx2=458), @Meta.Expr(xkind=5, alts={460, 461}),
    @Meta.Expr(xkind=5, alts={459, 462}), @Meta.Expr(subx1=2016), @Meta.Expr(subx1=2017),
    @Meta.Expr(xkind=1, subx1=464, subx2=465), @Meta.Expr(xkind=5, alts={465, 466}),
    @Meta.Expr(xkind=5, alts={464, 467})
  }
)

final public class Annotations  {
	public static void main(String[] args) {
		Meta.FregePackage p = Annotations.class.getAnnotation(Meta.FregePackage.class);
		System.out.print("annotation present: ");
		System.out.println(p != null);
	}
}
