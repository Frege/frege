#!/bin/sh
#
# usage: ./scripts/lein-deploy.sh
#
# creates an outline project.clj file based on the current fregec.jar
# version and deploys it to org.frege-lang/frege on Clojars
#
# you must already have a correctly configured Clojars account with
# your deployment keys in place!

if test -f fregec.jar
then
    echo "Found fregec.jar."
else
    echo "error: This must be run in the folder that contains fregec.jar."
    exit 1
fi

frege_version=`java -jar fregec.jar -version`
echo "Looks like you have Frege version $frege_version..."
frege_major=`echo $frege_version | sed 's;-.*;;'`
# build project.clj:
( echo "(defproject org.frege-lang/frege \"${frege_major}-SNAPSHOT\"";
  echo ":description \"Frege Compiler and Runtime\"";
  echo ":url \"https://github.com/Frege/frege\"";
  echo ":license {:name \"BSD 3-clause\"";
  echo "  :url \"http://opensource.org/licenses/BSD-3-Clause\"}";
  echo ":deploy-repositories {\"snapshots\"";
  echo "  {:url \"https://oss.sonatype.org/content/repositories/snapshots/\" :creds :gpg}}";
  echo ")" ) > project.clj
echo ""
echo "Here's the project.clj file I built:"
cat project.clj

# deploy to Sonatype:
echo ""
echo "Attempting to deploy to Sonatype Snapshots..."
lein pom
lein deploy snapshots org.frege-lang/frege ${frege_major}-SNAPSHOT fregec.jar pom.xml

# cleanup:
rm -rf pom.xml pom.xml.asc project.clj
