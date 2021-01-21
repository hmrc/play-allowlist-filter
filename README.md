
play-allowlist-filter
=====================

This library includes a ```Filter``` for the Play! framework which can be used to block users whose IP addresses are not on a predetermined allowlist.

Currently the only implementation of allowlisting available uses the IP from the ```True-Client-IP``` header provided by Akamai.

Getting Started
--------
1. Import the library:

  ```scala
  resolvers += Resolver.bintrayRepo("hmrc", "releases")
  libraryDependencies ++= Seq(
    "uk.gov.hmrc" %% "play-allowlist-filter" % "0.1.0"
  )
  ```
  
2. Implement the filter trait, for example:

  ```scala
  object AllowlistFilter extends AkamaiAllowlistFilter {
    override val allowlist: Seq[String] = Seq("127.0.0.1")
    override val destination: Call = Call("GET", "https://www.gov.uk")
  }
  ```

3. Add the filter to your ```Application```'s list of filters, for example:

  ```scala
  object MyGlobal extends WithFilters(AllowlistFilter)
  ```

4. Done

---

Bear in mind that as this uses the Akamai ```True-Client-IP``` header, you may wish to exclude the filter from pre-live environments. There are various ways to do this such as only including the filter based on some config field:

```scala
object TestGlobal extends GlobalSettings {

  val myFilters: Seq[Filter] = {
    Seq(SomeFilter, AnotherFilter) ++
    Play.configuration.getBoolean("shouldAllowlist").map {
      _ => Seq(AllowlistFilter)
    }.getOrElse(Seq.empty)
  }

  override def doFilter(next: EssentialAction): EssentialAction = {
    Filters(super.doFilter(next), myFilters: _*)
  }

}
```

You may also wish to exclude certain paths in your application from being filtered such as healthcheck routes. This can be done by implementing the ```excludedPaths: Seq[Call]``` field in the filter:

```scala
object AllowlistFilter extends AkamaiAllowlistFilter {
  override val allowlist: Seq[String] = Seq("127.0.0.1")
  override val destination: Call = Call("GET", "https://www.gov.uk")
  override val excludedPaths: Seq[Call] = Seq(Call("GET", "/healthcheck"))
}
```

===

