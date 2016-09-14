grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        // Default plugin repository changed
        mavenRepo "https://repo.grails.org/grails/plugins"
        mavenRepo "https://repo.grails.org/grails/core"
        mavenRepo "http://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-support"
        //mavenRepo "https://oss.sonatype.org/content/repositories/releases/"
        //mavenRepo "http://repo.spring.io/milestone"
        //mavenRepo "https://repository.jboss.org/maven2/"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.springsource.com/maven/bundles/release"
        //mavenRepo "http://repository.springsource.com/maven/bundles/external"
    }

    def gebVersion = "0.9.2"
    def seleniumVersion = "2.35.0"

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "org.gebish:geb-spock:$gebVersion"
        test "org.seleniumhq.selenium:selenium-support:2.40.0"

        // runtime 'mysql:mysql-connector-java:5.1.22'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.3"
        runtime ":resources:1.2"
        runtime ":database-migration:1.3.2"

        build ":tomcat:$grailsVersion"

        compile ':cache:1.0.1'
        compile ":google-chart:0.4.8"

        test ':code-coverage:1.2.5'
        test ":geb:$gebVersion"
        test (":spock:0.7") {
            exclude "spock-grails-support"
        }
    }

}
