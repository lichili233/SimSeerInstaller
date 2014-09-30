SimSeerX Installer
==================

This is a small Java program that downloads the [Play Framework] and [Solr] 
and unpacks their Zip files. Later on this program will feature 
key functionalities below:

*  Configuration for environment variables
*  Pull Github similarity functions used for [SimSeerX]
*  Dynamically choose versions for subcomponents
*  Assign parameters for subcomponents as needed
*  Put everything together

Note: the underlying [downloader] and [decompresser] utilities are from codejava.net.

To Use
------
javac InstallerExec.java
java InstallerExec

(Or simply make it into a JAR, since it is an executable program)

To Do
-----
All the above, and those below:

*  Further debug UnzipUtility to check issues and edge cases
*  Remove unnecessary code (extra exception handling, etc.)
*  Fix the UI alignment issues
*  Add incremental feedback on the download process
*  Move all intermediate outputs to UI instead of console
*  Add support to Windows environments
*  Remove extra dependencies

[Play Framework]:https://www.playframework.com/
[Solr]:http://lucene.apache.org/solr/
[downloader]:http://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
[decompresser]:http://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java