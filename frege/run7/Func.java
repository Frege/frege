package frege.run7;
import frege.run.Kind;
import frege.run7.Lazy;

public class Func  {
  public interface U<𝓐, 𝓑> 
    extends Lazy<Func.U<𝓐, 𝓑>>, Kind.U<Func.U<𝓐, ?>, 𝓑>, Kind.B<Func.U<?, ?>, 𝓐, 𝓑>
   {
    public Lazy<𝓑> apply(final Lazy<𝓐> a) ;
    public Func.U<𝓐, 𝓑> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑> implements Func.U<𝓐, 𝓑> {
      public Func.U<𝓐, 𝓑> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.U<𝓐, 𝓑>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓑> apply(final Lazy<𝓐> a) ;
    }
  }
  public interface B<𝓐, 𝓑, 𝓒> 
    extends Lazy<Func.B<𝓐, 𝓑, 𝓒>>, Kind.U<Func.B<𝓐, 𝓑, ?>, 𝓒>,
      Kind.B<Func.B<𝓐, ?, ?>, 𝓑, 𝓒>, Kind.T<Func.B<?, ?, ?>, 𝓐, 𝓑, 𝓒>
   {
    public Lazy<𝓒> apply(final Lazy<𝓐> a, final Lazy<𝓑> b) ;
    public Func.B<𝓐, 𝓑, 𝓒> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒> implements Func.B<𝓐, 𝓑, 𝓒> {
      public Func.B<𝓐, 𝓑, 𝓒> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.B<𝓐, 𝓑, 𝓒>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓒> apply(final Lazy<𝓐> a, final Lazy<𝓑> b) ;
    }
  }
  public interface T<𝓐, 𝓑, 𝓒, 𝓓> 
    extends Lazy<Func.T<𝓐, 𝓑, 𝓒, 𝓓>>, Kind.U<Func.T<𝓐, 𝓑, 𝓒, ?>, 𝓓>,
      Kind.B<Func.T<𝓐, 𝓑, ?, ?>, 𝓒, 𝓓>, Kind.T<Func.T<𝓐, ?, ?, ?>, 𝓑, 𝓒, 𝓓>,
      Kind.Q<Func.T<?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓>
   {
    public Lazy<𝓓> apply(final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c) ;
    public Func.T<𝓐, 𝓑, 𝓒, 𝓓> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓> implements Func.T<𝓐, 𝓑, 𝓒, 𝓓> {
      public Func.T<𝓐, 𝓑, 𝓒, 𝓓> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.T<𝓐, 𝓑, 𝓒, 𝓓>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓓> apply(final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c) ;
    }
  }
  public interface Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔> 
    extends Lazy<Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>>, Kind.U<Func.Q<𝓐, 𝓑, 𝓒, 𝓓, ?>, 𝓔>,
      Kind.B<Func.Q<𝓐, 𝓑, 𝓒, ?, ?>, 𝓓, 𝓔>, Kind.T<Func.Q<𝓐, 𝓑, ?, ?, ?>, 𝓒, 𝓓, 𝓔>,
      Kind.Q<Func.Q<𝓐, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔>,
      Kind.V<Func.Q<?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔>
   {
    public Lazy<𝓔> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d
    ) ;
    public Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔> implements Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔> {
      public Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓔> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d
      ) ;
    }
  }
  public interface V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> 
    extends Lazy<Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>>, Kind.U<Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?>, 𝓕>,
      Kind.B<Func.V<𝓐, 𝓑, 𝓒, 𝓓, ?, ?>, 𝓔, 𝓕>,
      Kind.T<Func.V<𝓐, 𝓑, 𝓒, ?, ?, ?>, 𝓓, 𝓔, 𝓕>,
      Kind.Q<Func.V<𝓐, 𝓑, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕>,
      Kind.V<Func.V<𝓐, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>,
      Kind.VI<Func.V<?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>
   {
    public Lazy<𝓕> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e
    ) ;
    public Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> 
      implements Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>
     {
      public Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓕> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e
      ) ;
    }
  }
  public interface VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> 
    extends Lazy<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>>,
      Kind.U<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?>, 𝓖>,
      Kind.B<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?>, 𝓕, 𝓖>,
      Kind.T<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?>, 𝓔, 𝓕, 𝓖>,
      Kind.Q<Func.VI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖>,
      Kind.V<Func.VI<𝓐, 𝓑, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>,
      Kind.VI<Func.VI<𝓐, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>,
      Kind.VII<Func.VI<?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>
   {
    public Lazy<𝓖> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f
    ) ;
    public Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> 
      implements Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>
     {
      public Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓖> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f
      ) ;
    }
  }
  public interface VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> 
    extends Lazy<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>>,
      Kind.U<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?>, 𝓗>,
      Kind.B<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?>, 𝓖, 𝓗>,
      Kind.T<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?>, 𝓕, 𝓖, 𝓗>,
      Kind.Q<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗>,
      Kind.V<Func.VII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>,
      Kind.VI<Func.VII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>,
      Kind.VII<Func.VII<𝓐, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>,
      Kind.VIII<Func.VII<?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>
   {
    public Lazy<𝓗> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g
    ) ;
    public Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> 
      implements Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>
     {
      public Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓗> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g
      ) ;
    }
  }
  public interface VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> 
    extends Lazy<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>>,
      Kind.U<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?>, 𝓘>,
      Kind.B<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?>, 𝓗, 𝓘>,
      Kind.T<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?>, 𝓖, 𝓗, 𝓘>,
      Kind.Q<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘>,
      Kind.V<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>,
      Kind.VI<Func.VIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>,
      Kind.VII<Func.VIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>,
      Kind.VIII<Func.VIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>,
      Kind.IX<Func.VIII<?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>
   {
    public Lazy<𝓘> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h
    ) ;
    public Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> 
      implements Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>
     {
      public Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓘> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h
      ) ;
    }
  }
  public interface IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> 
    extends Lazy<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>>,
      Kind.U<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?>, 𝓙>,
      Kind.B<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?>, 𝓘, 𝓙>,
      Kind.T<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?>, 𝓗, 𝓘, 𝓙>,
      Kind.Q<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.V<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.VI<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.VII<Func.IX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.VIII<Func.IX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.IX<Func.IX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>,
      Kind.X<Func.IX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>
   {
    public Lazy<𝓙> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i
    ) ;
    public Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> 
      implements Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>
     {
      public Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓙> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i
      ) ;
    }
  }
  public interface X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> 
    extends Lazy<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>>,
      Kind.U<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?>, 𝓚>,
      Kind.B<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?>, 𝓙, 𝓚>,
      Kind.T<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?>, 𝓘, 𝓙, 𝓚>,
      Kind.Q<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?>, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.V<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.VI<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.VII<Func.X<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.VIII<Func.X<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.IX<Func.X<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.X<Func.X<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>,
      Kind.XI<
        Func.X<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
      >
   {
    public Lazy<𝓚> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j
    ) ;
    public Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> 
      implements Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>
     {
      public Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓚> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j
      ) ;
    }
  }
  public interface XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> 
    extends Lazy<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>>,
      Kind.U<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?>, 𝓛>,
      Kind.B<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?>, 𝓚, 𝓛>,
      Kind.T<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?>, 𝓙, 𝓚, 𝓛>,
      Kind.Q<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?>, 𝓘, 𝓙, 𝓚, 𝓛>,
      Kind.V<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?>, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>,
      Kind.VI<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>,
      Kind.VII<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>,
      Kind.VIII<
        Func.XI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛
      >,
      Kind.IX<
        Func.XI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
      >,
      Kind.X<
        Func.XI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
      >,
      Kind.XI<
        Func.XI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
      >,
      Kind.XII<
        Func.XI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
      >
   {
    public Lazy<𝓛> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k
    ) ;
    public Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> 
      implements Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>
     {
      public Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓛> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k
      ) ;
    }
  }
  public interface XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> 
    extends Lazy<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>>,
      Kind.U<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?>, 𝓜>,
      Kind.B<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?>, 𝓛, 𝓜>,
      Kind.T<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?>, 𝓚, 𝓛, 𝓜>,
      Kind.Q<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?>, 𝓙, 𝓚, 𝓛, 𝓜>,
      Kind.V<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?>, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>,
      Kind.VI<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?>, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>,
      Kind.VII<
        Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?>, 𝓖, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.VIII<
        Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.IX<
        Func.XII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.X<
        Func.XII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.XI<
        Func.XII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.XII<
        Func.XII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >,
      Kind.XIII<
        Func.XII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
      >
   {
    public Lazy<𝓜> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l
    ) ;
    public Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> 
      implements Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>
     {
      public Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>> asThunk() {
        return null;
      }
      public abstract Lazy<𝓜> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l
      ) ;
    }
  }
  public interface XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> 
    extends Lazy<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>>,
      Kind.U<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?>, 𝓝>,
      Kind.B<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?>, 𝓜, 𝓝>,
      Kind.T<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?>, 𝓛, 𝓜, 𝓝>,
      Kind.Q<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?>, 𝓚, 𝓛, 𝓜, 𝓝>,
      Kind.V<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?>, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>,
      Kind.VI<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.VII<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.VIII<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.IX<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.X<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.XI<
        Func.XIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.XII<
        Func.XIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.XIII<
        Func.XIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >,
      Kind.XIV<
        Func.XIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
      >
   {
    public Lazy<𝓝> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m
    ) ;
    public Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> 
      implements Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>
     {
      public Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓝> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m
      ) ;
    }
  }
  public interface XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> 
    extends Lazy<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>>,
      Kind.U<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?>, 𝓞>,
      Kind.B<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?>, 𝓝, 𝓞>,
      Kind.T<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?>, 𝓜, 𝓝, 𝓞>,
      Kind.Q<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?>, 𝓛, 𝓜, 𝓝, 𝓞>,
      Kind.V<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.VI<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.VII<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.VIII<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.IX<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.X<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.XI<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.XII<
        Func.XIV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.XIII<
        Func.XIV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.XIV<
        Func.XIV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >,
      Kind.XV<
        Func.XIV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
      >
   {
    public Lazy<𝓞> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n
    ) ;
    public Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> call() ;
    public boolean isShared() ;
    public static abstract class D<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> 
      implements Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>
     {
      public Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓞> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n
      ) ;
    }
  }
  public interface XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> 
    extends Lazy<Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>>,
      Kind.U<Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?>, 𝓟>,
      Kind.B<Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?>, 𝓞, 𝓟>,
      Kind.T<Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?>, 𝓝, 𝓞, 𝓟>,
      Kind.Q<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟
      >,
      Kind.V<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.VI<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.VII<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.VIII<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.IX<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.X<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XI<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XII<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XIII<
        Func.XV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XIV<
        Func.XV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XV<
        Func.XV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >,
      Kind.XVI<
        Func.XV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
      >
   {
    public Lazy<𝓟> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o
    ) ;
    public Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > implements Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> {
      public Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓟> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o
      ) ;
    }
  }
  public interface XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> 
    extends Lazy<Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>>,
      Kind.U<Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?>, 𝓠>,
      Kind.B<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?>, 𝓟,
        𝓠
      >,
      Kind.T<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?>, 𝓞,
        𝓟, 𝓠
      >,
      Kind.Q<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?>, 𝓝,
        𝓞, 𝓟, 𝓠
      >,
      Kind.V<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.VI<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.VII<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.VIII<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.IX<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.X<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XI<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XII<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XIII<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XIV<
        Func.XVI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XV<
        Func.XVI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XVI<
        Func.XVI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
      >,
      Kind.XVII<
        Func.XVI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠
      >
   {
    public Lazy<𝓠> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p
    ) ;
    public Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠
    > implements Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> {
      public Func.XVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓠> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p
      ) ;
    }
  }
  public interface XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡> 
    extends Lazy<Func.XVII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡
      >>,
      Kind.U<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?>, 𝓡
      >,
      Kind.B<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?>, 𝓠,
        𝓡
      >,
      Kind.T<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?>, 𝓟,
        𝓠, 𝓡
      >,
      Kind.Q<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?>, 𝓞,
        𝓟, 𝓠, 𝓡
      >,
      Kind.V<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?>, 𝓝,
        𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.VI<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.VII<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.VIII<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.IX<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.X<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XI<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XII<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XIII<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XIV<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XV<
        Func.XVII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XVI<
        Func.XVII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
      >,
      Kind.XVII<
        Func.XVII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡
      >,
      Kind.XVIII<
        Func.XVII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡
      >
   {
    public Lazy<𝓡> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q
    ) ;
    public Func.XVII<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡
    > 
      implements Func.XVII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡
      >
     {
      public Func.XVII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓡> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p, final Lazy<𝓠> q
      ) ;
    }
  }
  public interface XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > 
    extends Lazy<Func.XVIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢
      >>,
      Kind.U<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, ?>,
        𝓢
      >,
      Kind.B<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?>,
        𝓡, 𝓢
      >,
      Kind.T<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?>, 𝓠,
        𝓡, 𝓢
      >,
      Kind.Q<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?>, 𝓟,
        𝓠, 𝓡, 𝓢
      >,
      Kind.V<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?>, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.VI<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?>, 𝓝,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.VII<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.VIII<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.IX<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.X<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XI<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XII<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XIII<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XIV<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XV<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XVI<
        Func.XVIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
      >,
      Kind.XVII<
        Func.XVIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢
      >,
      Kind.XVIII<
        Func.XVIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢
      >,
      Kind.XIX<
        Func.XVIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢
      >
   {
    public Lazy<𝓢> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r
    ) ;
    public Func.XVIII<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢
    > 
      implements Func.XVIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢
      >
     {
      public Func.XVIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓢> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r
      ) ;
    }
  }
  public interface XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > 
    extends Lazy<Func.XIX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >>,
      Kind.U<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, ?>,
        𝓣
      >,
      Kind.B<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, ?, ?>,
        𝓢, 𝓣
      >,
      Kind.T<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?, ?>,
        𝓡, 𝓢, 𝓣
      >,
      Kind.Q<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?, ?>,
        𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.V<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?, ?>, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.VI<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?, ?>, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.VII<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?>, 𝓝,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.VIII<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.IX<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.X<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XI<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XII<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XIII<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XIV<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XV<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XVI<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >,
      Kind.XVII<
        Func.XIX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣
      >,
      Kind.XVIII<
        Func.XIX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣
      >,
      Kind.XIX<
        Func.XIX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣
      >,
      Kind.XX<
        Func.XIX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣
      >
   {
    public Lazy<𝓣> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s
    ) ;
    public Func.XIX<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣
    > 
      implements Func.XIX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      >
     {
      public Func.XIX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣>
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓣> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s
      ) ;
    }
  }
  public interface XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > 
    extends Lazy<Func.XX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >>,
      Kind.U<
        Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?
        >,
        𝓤
      >,
      Kind.B<
        Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?
        >,
        𝓣, 𝓤
      >,
      Kind.T<
        Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤
      >,
      Kind.Q<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?, ?, ?>,
        𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.V<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?, ?, ?>,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.VI<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?, ?, ?>,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.VII<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?, ?, ?>,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.VIII<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓝,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.IX<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.X<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XI<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XII<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XIII<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XIV<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XV<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XVI<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XVII<
        Func.XX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤
      >,
      Kind.XVIII<
        Func.XX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤
      >,
      Kind.XIX<
        Func.XX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤
      >,
      Kind.XX<
        Func.XX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤
      >,
      Kind.XXI<
        Func.XX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >
   {
    public Lazy<𝓤> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t
    ) ;
    public Func.XX<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > 
      implements Func.XX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      >
     {
      public Func.XX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓤> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t
      ) ;
    }
  }
  public interface XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > 
    extends Lazy<Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >>,
      Kind.U<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?
        >,
        𝓥
      >,
      Kind.B<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?
        >,
        𝓤, 𝓥
      >,
      Kind.T<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥
      >,
      Kind.Q<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.V<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.VI<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.VII<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.VIII<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.IX<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.X<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XI<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XII<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XIII<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XIV<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XV<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XVI<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XVII<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥
      >,
      Kind.XVIII<
        Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥
      >,
      Kind.XIX<
        Func.XXI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥
      >,
      Kind.XX<
        Func.XXI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XXI<
        Func.XXI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >,
      Kind.XXII<
        Func.XXI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >
   {
    public Lazy<𝓥> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u
    ) ;
    public Func.XXI<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > 
      implements Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      >
     {
      public Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓥> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t, final Lazy<𝓤> u
      ) ;
    }
  }
  public interface XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > 
    extends Lazy<Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >>,
      Kind.U<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?
        >,
        𝓦
      >,
      Kind.B<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?
        >,
        𝓥, 𝓦
      >,
      Kind.T<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?
        >,
        𝓤, 𝓥, 𝓦
      >,
      Kind.Q<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.V<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.VI<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.VII<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.VIII<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.IX<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.X<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XI<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XII<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XIII<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XIV<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XV<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XVI<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
        𝓦
      >,
      Kind.XVII<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦
      >,
      Kind.XVIII<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦
      >,
      Kind.XIX<
        Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥, 𝓦
      >,
      Kind.XX<
        Func.XXII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XXI<
        Func.XXII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XXII<
        Func.XXII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >,
      Kind.XXIII<
        Func.XXII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >
   {
    public Lazy<𝓦> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u, final Lazy<𝓥> v
    ) ;
    public Func.XXII<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > 
      implements Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      >
     {
      public Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓦> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
        final Lazy<𝓤> u, final Lazy<𝓥> v
      ) ;
    }
  }
  public interface XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > 
    extends Lazy<Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >>,
      Kind.U<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?
        >,
        𝓧
      >,
      Kind.B<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?
        >,
        𝓦, 𝓧
      >,
      Kind.T<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?
        >,
        𝓥, 𝓦, 𝓧
      >,
      Kind.Q<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?
        >,
        𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.V<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.VI<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.VII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.VIII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.IX<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.X<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XI<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XIII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XIV<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XV<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XVI<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
        𝓧
      >,
      Kind.XVII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
        𝓦, 𝓧
      >,
      Kind.XVIII<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦, 𝓧
      >,
      Kind.XIX<
        Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XX<
        Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XXI<
        Func.XXIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XXII<
        Func.XXIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XXIII<
        Func.XXIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >,
      Kind.XXIV<
        Func.XXIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >
   {
    public Lazy<𝓧> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w
    ) ;
    public Func.XXIII<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > 
      implements Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      >
     {
      public Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓧> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
        final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w
      ) ;
    }
  }
  public interface XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > 
    extends Lazy<Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >>,
      Kind.U<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?
        >,
        𝓨
      >,
      Kind.B<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?
        >,
        𝓧, 𝓨
      >,
      Kind.T<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?
        >,
        𝓦, 𝓧, 𝓨
      >,
      Kind.Q<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?
        >,
        𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.V<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?
        >,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.VI<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.VII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.VIII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.IX<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.X<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XI<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XIII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XIV<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XV<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XVI<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
        𝓨
      >,
      Kind.XVII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
        𝓧, 𝓨
      >,
      Kind.XVIII<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
        𝓦, 𝓧, 𝓨
      >,
      Kind.XIX<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XX<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XXI<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XXII<
        Func.XXIV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XXIII<
        Func.XXIV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XXIV<
        Func.XXIV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >,
      Kind.XXV<
        Func.XXIV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >
   {
    public Lazy<𝓨> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x
    ) ;
    public Func.XXIV<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > 
      implements Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      >
     {
      public Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓨> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
        final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x
      ) ;
    }
  }
  public interface XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > 
    extends Lazy<Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >>,
      Kind.U<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, ?
        >,
        𝓩
      >,
      Kind.B<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?, ?
        >,
        𝓨, 𝓩
      >,
      Kind.T<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?, ?
        >,
        𝓧, 𝓨, 𝓩
      >,
      Kind.Q<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?, ?
        >,
        𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.V<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?, ?
        >,
        𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.VI<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?, ?
        >,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.VII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.VIII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.IX<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.X<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XI<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XIII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XIV<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XV<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XVI<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨,
        𝓩
      >,
      Kind.XVII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
        𝓨, 𝓩
      >,
      Kind.XVIII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
        𝓧, 𝓨, 𝓩
      >,
      Kind.XIX<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
        𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XX<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXI<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXIII<
        Func.XXV<
          𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXIV<
        Func.XXV<
          𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?
        >,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXV<
        Func.XXV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >,
      Kind.XXVI<
        Func.XXV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >
   {
    public Lazy<𝓩> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x, final Lazy<𝓨> y
    ) ;
    public Func.XXV<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > 
      implements Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      >
     {
      public Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<𝓩> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
        final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x, final Lazy<𝓨> y
      ) ;
    }
  }
  public interface XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > 
    extends Lazy<Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >>,
      Kind.U<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, ?
        >,
        Ω
      >,
      Kind.B<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, ?, ?
        >,
        𝓩, Ω
      >,
      Kind.T<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?, ?, ?
        >,
        𝓨, 𝓩, Ω
      >,
      Kind.Q<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?, ?, ?
        >,
        𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.V<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?, ?, ?
        >,
        𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.VI<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?, ?, ?
        >,
        𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.VII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.VIII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.IX<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.X<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XI<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XIII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XIV<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XV<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XVI<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩,
        Ω
      >,
      Kind.XVII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨,
        𝓩, Ω
      >,
      Kind.XVIII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
        𝓨, 𝓩, Ω
      >,
      Kind.XIX<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
        𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XX<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
        𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXI<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
        𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
        𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXIII<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
        𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXIV<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
        𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXV<
        Func.XXVI<
          𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
        𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXVI<
        Func.XXVI<
          𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
        𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >,
      Kind.XXVII<
        Func.XXVI<
          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
          ?, ?, ?, ?, ?, ?, ?, ?
        >,
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >
   {
    public Lazy<Ω> apply(
      final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d, final Lazy<𝓔> e,
      final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h, final Lazy<𝓘> i, final Lazy<𝓙> j,
      final Lazy<𝓚> k, final Lazy<𝓛> l, final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o,
      final Lazy<𝓟> p, final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
      final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x, final Lazy<𝓨> y,
      final Lazy<𝓩> z
    ) ;
    public Func.XXVI<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > call() ;
    public boolean isShared() ;
    public static abstract class D<
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > 
      implements Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      >
     {
      public Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
      > call() {
        return this;
      }
      public boolean isShared() {
        return true;
      }
      public Thunk<
        Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >
      > asThunk() {
        return null;
      }
      public abstract Lazy<Ω> apply(
        final Lazy<𝓐> a, final Lazy<𝓑> b, final Lazy<𝓒> c, final Lazy<𝓓> d,
        final Lazy<𝓔> e, final Lazy<𝓕> f, final Lazy<𝓖> g, final Lazy<𝓗> h,
        final Lazy<𝓘> i, final Lazy<𝓙> j, final Lazy<𝓚> k, final Lazy<𝓛> l,
        final Lazy<𝓜> m, final Lazy<𝓝> n, final Lazy<𝓞> o, final Lazy<𝓟> p,
        final Lazy<𝓠> q, final Lazy<𝓡> r, final Lazy<𝓢> s, final Lazy<𝓣> t,
        final Lazy<𝓤> u, final Lazy<𝓥> v, final Lazy<𝓦> w, final Lazy<𝓧> x,
        final Lazy<𝓨> y, final Lazy<𝓩> z
      ) ;
    }
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑> Func.U<
    𝓐, 𝓑
  > coerceU(final Kind.U<Func.U<𝓐, ?>, 𝓑> it) {
    return (Func.U<𝓐, 𝓑>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑> Func.U<
    𝓐, 𝓑
  > coerceU(final Kind.B<Func.U<?, ?>, 𝓐, 𝓑> it) {
    return (Func.U<𝓐, 𝓑>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒> Func.B<
    𝓐, 𝓑, 𝓒
  > coerceB(final Kind.U<Func.B<𝓐, 𝓑, ?>, 𝓒> it) {
    return (Func.B<𝓐, 𝓑, 𝓒>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒> Func.B<
    𝓐, 𝓑, 𝓒
  > coerceB(final Kind.B<Func.B<𝓐, ?, ?>, 𝓑, 𝓒> it) {
    return (Func.B<𝓐, 𝓑, 𝓒>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒> Func.B<
    𝓐, 𝓑, 𝓒
  > coerceB(final Kind.T<Func.B<?, ?, ?>, 𝓐, 𝓑, 𝓒> it) {
    return (Func.B<𝓐, 𝓑, 𝓒>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓> Func.T<
    𝓐, 𝓑, 𝓒, 𝓓
  > coerceT(final Kind.U<Func.T<𝓐, 𝓑, 𝓒, ?>, 𝓓> it) {
    return (Func.T<𝓐, 𝓑, 𝓒, 𝓓>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓> Func.T<
    𝓐, 𝓑, 𝓒, 𝓓
  > coerceT(final Kind.B<Func.T<𝓐, 𝓑, ?, ?>, 𝓒, 𝓓> it) {
    return (Func.T<𝓐, 𝓑, 𝓒, 𝓓>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓> Func.T<
    𝓐, 𝓑, 𝓒, 𝓓
  > coerceT(final Kind.T<Func.T<𝓐, ?, ?, ?>, 𝓑, 𝓒, 𝓓> it) {
    return (Func.T<𝓐, 𝓑, 𝓒, 𝓓>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓> Func.T<
    𝓐, 𝓑, 𝓒, 𝓓
  > coerceT(final Kind.Q<Func.T<?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓> it) {
    return (Func.T<𝓐, 𝓑, 𝓒, 𝓓>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔> Func.Q<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔
  > coerceQ(final Kind.U<Func.Q<𝓐, 𝓑, 𝓒, 𝓓, ?>, 𝓔> it) {
    return (Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔> Func.Q<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔
  > coerceQ(final Kind.B<Func.Q<𝓐, 𝓑, 𝓒, ?, ?>, 𝓓, 𝓔> it) {
    return (Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔> Func.Q<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔
  > coerceQ(final Kind.T<Func.Q<𝓐, 𝓑, ?, ?, ?>, 𝓒, 𝓓, 𝓔> it) {
    return (Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔> Func.Q<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔
  > coerceQ(final Kind.Q<Func.Q<𝓐, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔> it) {
    return (Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔> Func.Q<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔
  > coerceQ(final Kind.V<Func.Q<?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔> it) {
    return (Func.Q<𝓐, 𝓑, 𝓒, 𝓓, 𝓔>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.U<Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?>, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.B<Func.V<𝓐, 𝓑, 𝓒, 𝓓, ?, ?>, 𝓔, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.T<Func.V<𝓐, 𝓑, 𝓒, ?, ?, ?>, 𝓓, 𝓔, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.Q<Func.V<𝓐, 𝓑, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.V<Func.V<𝓐, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> Func.V<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕
  > coerceV(final Kind.VI<Func.V<?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕> it) {
    return (Func.V<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.U<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?>, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.B<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?>, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.T<Func.VI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?>, 𝓔, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.Q<Func.VI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.V<Func.VI<𝓐, 𝓑, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.VI<Func.VI<𝓐, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> Func.VI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖
  > coerceVI(final Kind.VII<Func.VI<?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖> it) {
    return (Func.VI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.U<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?>, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.B<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?>, 𝓖, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.T<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?>, 𝓕, 𝓖, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.Q<Func.VII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.V<Func.VII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(final Kind.VI<Func.VII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> it) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(
    final Kind.VII<Func.VII<𝓐, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> it
  ) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> Func.VII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗
  > coerceVII(
    final Kind.VIII<Func.VII<?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗> it
  ) {
    return (Func.VII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.U<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?>, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.B<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?>, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.T<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?>, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.Q<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.V<Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.VI<Func.VIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.VII<Func.VIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.VIII<Func.VIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘
  > Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> coerceVIII(
    final Kind.IX<Func.VIII<?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘> it
  ) {
    return (Func.VIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.U<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?>, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.B<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?>, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.T<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?>, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.Q<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.V<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.VI<Func.IX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.VII<Func.IX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.VIII<Func.IX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.IX<Func.IX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙
  > Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙> coerceIX(
    final Kind.X<
      Func.IX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙
    > it
  ) {
    return (Func.IX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.U<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?>, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.B<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?>, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.T<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?>, 𝓘, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.Q<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?>, 𝓗, 𝓘, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.V<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.VI<Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.VII<Func.X<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.VIII<
      Func.X<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗,
      𝓘, 𝓙, 𝓚
    > it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.IX<
      Func.X<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚
    > it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.X<
      Func.X<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚
    > it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚
  > Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚> coerceX(
    final Kind.XI<
      Func.X<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚
    > it
  ) {
    return (Func.X<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.U<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?>, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.B<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?>, 𝓚, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.T<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?>, 𝓙, 𝓚, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.Q<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?>, 𝓘, 𝓙, 𝓚, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.V<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?>, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.VI<Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?>, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.VII<
      Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.VIII<
      Func.XI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.IX<
      Func.XI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.X<
      Func.XI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.XI<
      Func.XI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
  > Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛> coerceXI(
    final Kind.XII<
      Func.XI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛
    > it
  ) {
    return (Func.XI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.U<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?>, 𝓜> it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.B<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?>, 𝓛, 𝓜> it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.T<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?>, 𝓚, 𝓛, 𝓜> it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.Q<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?>, 𝓙, 𝓚, 𝓛, 𝓜> it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.V<Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?>, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.VI<
      Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?>, 𝓗, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.VII<
      Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?>, 𝓖, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.VIII<
      Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.IX<
      Func.XII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.X<
      Func.XII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.XI<
      Func.XII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.XII<
      Func.XII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
  > Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜> coerceXII(
    final Kind.XIII<
      Func.XII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜
    > it
  ) {
    return (Func.XII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.U<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?>, 𝓝> it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.B<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?>, 𝓜, 𝓝> it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.T<Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?>, 𝓛, 𝓜, 𝓝> it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.Q<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.V<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.VI<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.VII<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.VIII<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.IX<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.X<
      Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.XI<
      Func.XIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.XII<
      Func.XIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.XIII<
      Func.XIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
  > Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝> coerceXIII(
    final Kind.XIV<
      Func.XIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝
    > it
  ) {
    return (Func.XIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.U<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?>, 𝓞> it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.B<Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?>, 𝓝, 𝓞> it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.T<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?>, 𝓜,
      𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.Q<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.V<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.VI<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.VII<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.VIII<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.IX<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.X<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.XI<
      Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.XII<
      Func.XIV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.XIII<
      Func.XIV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.XIV<
      Func.XIV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
  > Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞> coerceXIV(
    final Kind.XV<
      Func.XIV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞
    > it
  ) {
    return (Func.XIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.U<Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?>, 𝓟> it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.B<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?>, 𝓞,
      𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.T<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?>, 𝓝,
      𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.Q<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.V<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.VI<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.VII<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.VIII<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.IX<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.X<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XI<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XII<
      Func.XV<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XIII<
      Func.XV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XIV<
      Func.XV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XV<
      Func.XV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
  > Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟> coerceXV(
    final Kind.XVI<
      Func.XV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟
    > it
  ) {
    return (Func.XV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.U<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?>, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.B<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?>, 𝓟,
      𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.T<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?>, 𝓞,
      𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.Q<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?>, 𝓝,
      𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.V<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.VI<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.VII<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.VIII<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.IX<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.X<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XI<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XII<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XIII<
      Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XIV<
      Func.XVI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XV<
      Func.XVI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XVI<
      Func.XVI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠
  > Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠> coerceXVI(
    final Kind.XVII<
      Func.XVI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠
    > it
  ) {
    return (Func.XVI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠>)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.U<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?>, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.B<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?>, 𝓠,
      𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.T<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?>, 𝓟,
      𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.Q<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?>, 𝓞,
      𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.V<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?>, 𝓝,
      𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.VI<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.VII<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.VIII<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.IX<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.X<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XI<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XII<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XIII<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XIV<
      Func.XVII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XV<
      Func.XVII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XVI<
      Func.XVII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XVII<
      Func.XVII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > Func.XVII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡
  > coerceXVII(
    final Kind.XVIII<
      Func.XVII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡
    > it
  ) {
    return (Func.XVII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.U<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, ?>, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.B<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?>, 𝓡,
      𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.T<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?>, 𝓠,
      𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.Q<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?>, 𝓟,
      𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.V<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?>, 𝓞,
      𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.VI<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?>, 𝓝,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.VII<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.VIII<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.IX<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.X<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XI<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XII<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XIII<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XIV<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XV<
      Func.XVIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XVI<
      Func.XVIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XVII<
      Func.XVIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XVIII<
      Func.XVIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > Func.XVIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢
  > coerceXVIII(
    final Kind.XIX<
      Func.XVIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢
    > it
  ) {
    return (Func.XVIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.U<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, ?>,
      𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.B<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, ?, ?>,
      𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.T<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?, ?>, 𝓡,
      𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.Q<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?, ?>, 𝓠,
      𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.V<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?, ?>, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.VI<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?, ?>, 𝓞,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.VII<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?>, 𝓝,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.VIII<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.IX<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.X<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XI<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XII<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XIII<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XIV<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XV<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XVI<
      Func.XIX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XVII<
      Func.XIX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XVIII<
      Func.XIX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XIX<
      Func.XIX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > Func.XIX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣
  > coerceXIX(
    final Kind.XX<
      Func.XIX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣
    > it
  ) {
    return (Func.XIX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.U<
      Func.XX<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?
      >,
      𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.B<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, ?, ?>,
      𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.T<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, ?, ?, ?>,
      𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.Q<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, ?, ?, ?, ?>,
      𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.V<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, ?, ?, ?, ?, ?>,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.VI<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?, ?, ?>, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.VII<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?, ?, ?>, 𝓞,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.VIII<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓝,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.IX<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓜,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.X<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XI<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XII<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XIII<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XIV<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XV<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XVI<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XVII<
      Func.XX<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XVIII<
      Func.XX<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XIX<
      Func.XX<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XX<
      Func.XX<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > Func.XX<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤
  > coerceXX(
    final Kind.XXI<
      Func.XX<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤
    > it
  ) {
    return (Func.XX<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.U<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?
      >,
      𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.B<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?
      >,
      𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.T<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.Q<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.V<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.VI<
      Func.XXI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.VII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, ?, ?, ?, ?, ?, ?, ?>,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.VIII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.IX<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.X<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XI<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓛,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓚,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XIII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓙,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XIV<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓘,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XV<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XVI<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XVII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XVIII<
      Func.XXI<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XIX<
      Func.XXI<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XX<
      Func.XXI<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XXI<
      Func.XXI<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > Func.XXI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
  > coerceXXI(
    final Kind.XXII<
      Func.XXI<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥
    > it
  ) {
    return (Func.XXI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.U<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?
      >,
      𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.B<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?
      >,
      𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.T<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?
      >,
      𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.Q<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.V<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.VI<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.VII<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.VIII<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.IX<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.X<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XI<
      Func.XXII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XII<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XIII<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XIV<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XV<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XVI<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓗,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XVII<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓖,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XVIII<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓕,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
      𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XIX<
      Func.XXII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓔,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XX<
      Func.XXII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XXI<
      Func.XXII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XXII<
      Func.XXII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > Func.XXII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
  > coerceXXII(
    final Kind.XXIII<
      Func.XXII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
    > it
  ) {
    return (Func.XXII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.U<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?
      >,
      𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.B<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?
      >,
      𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.T<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?
      >,
      𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.Q<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?
      >,
      𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.V<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.VI<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.VII<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.VIII<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.IX<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.X<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XI<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XII<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XIII<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XIV<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XV<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XVI<
      Func.XXIII<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XVII<
      Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
      𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XVIII<
      Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
      𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XIX<
      Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XX<
      Func.XXIII<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XXI<
      Func.XXIII<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓓,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XXII<
      Func.XXIII<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓒,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XXIII<
      Func.XXIII<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > Func.XXIII<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
  > coerceXXIII(
    final Kind.XXIV<
      Func.XXIII<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
    > it
  ) {
    return (Func.XXIII<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.U<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?
      >,
      𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.B<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?
      >,
      𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.T<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?
      >,
      𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.Q<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?
      >,
      𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.V<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?
      >,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.VI<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.VII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.VIII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.IX<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.X<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XI<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XIII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XIV<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XV<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XVI<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XVII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
      𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XVIII<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
      𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XIX<
      Func.XXIV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
      𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XX<
      Func.XXIV<𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XXI<
      Func.XXIV<𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XXII<
      Func.XXIV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XXIII<
      Func.XXIV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XXIV<
      Func.XXIV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓑,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > Func.XXIV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
  > coerceXXIV(
    final Kind.XXV<
      Func.XXIV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 𝓐,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
    > it
  ) {
    return (Func.XXIV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.U<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, ?
      >,
      𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.B<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?, ?
      >,
      𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.T<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?, ?
      >,
      𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.Q<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?, ?
      >,
      𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.V<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?, ?
      >,
      𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.VI<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?, ?
      >,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.VII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.VIII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.IX<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.X<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XI<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XIII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XIV<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XV<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XVI<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XVII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨,
      𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XVIII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
      𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XIX<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
      𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XX<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
      𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXI<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXII<
      Func.XXV<
        𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXIII<
      Func.XXV<𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXIV<
      Func.XXV<𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXV<
      Func.XXV<𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > Func.XXV<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
  > coerceXXV(
    final Kind.XXVI<
      Func.XXV<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>,
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
    > it
  ) {
    return (Func.XXV<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.U<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, ?
      >,
      Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.B<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, ?, ?
      >,
      𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.T<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, ?, ?, ?
      >,
      𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.Q<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, ?, ?, ?, ?
      >,
      𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.V<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, ?, ?, ?, ?, ?
      >,
      𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.VI<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, ?, ?, ?, ?, ?, ?
      >,
      𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.VII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, 𝓣, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.VIII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, 𝓢, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.IX<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, 𝓡, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.X<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, 𝓠, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XI<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        𝓟, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XIII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XIV<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XV<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XVI<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XVII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩,
      Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XVIII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨,
      𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XIX<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧,
      𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XX<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦,
      𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXI<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥,
      𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, 𝓔, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤,
      𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXIII<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, 𝓓, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢, 𝓣,
      𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXIV<
      Func.XXVI<
        𝓐, 𝓑, 𝓒, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡, 𝓢,
      𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXV<
      Func.XXVI<
        𝓐, 𝓑, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?, ?
      >,
      𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠, 𝓡,
      𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXVI<
      Func.XXVI<
        𝓐, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟, 𝓠,
      𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
  @SuppressWarnings("unchecked") final public static <
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > Func.XXVI<
    𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
    𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
  > coerceXXVI(
    final Kind.XXVII<
      Func.XXVI<
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
        ?, ?, ?, ?, ?, ?, ?
      >,
      𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞, 𝓟,
      𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
    > it
  ) {
    return (Func.XXVI<
          𝓐, 𝓑, 𝓒, 𝓓, 𝓔, 𝓕, 𝓖, 𝓗, 𝓘, 𝓙, 𝓚, 𝓛, 𝓜, 𝓝, 𝓞,
          𝓟, 𝓠, 𝓡, 𝓢, 𝓣, 𝓤, 𝓥, 𝓦, 𝓧, 𝓨, 𝓩, Ω
        >)it;
  }
}
