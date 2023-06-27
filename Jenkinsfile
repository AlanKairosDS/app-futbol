pipeline {
    agent any

    //Se define el archivo para realizar los escaneos de SonarQube
    //De preferencia es una configuracion realizada en Jenkins
    environment {
        scannerHome=tool "sonarqube-scanner"
        NEXUS_VERSION="nexus3"
        NEXUS_PROTOCOL="http"
        NEXUS_URL="127.0.0.1:8088"
        NEXUS_REPOSITORY="maven-alan-repo"
        NEXUS_CREDENTIAL_ID="nexus-user-credentials"
    }

    //Se define la version que se utilizara de Maven para construir el proyecto
    //De preferencia es una configuracion realizada en Jenkins
    tools {
        maven "jenkins-maven"
    }

    //Se definen todos los pasos que realizara el pipeline al momento de su ejecucion
    stages {
        //Paso que realiza la construccion del proyecto limpiando previamente las
        //carpeta de construcciones pasadas, saltandose la ejecucion de Tests
        stage('Build Project') {
            steps {
                sh "mvn -B -DskipTests clean package"
            }
        }

        //Paso que realiza la ejecucion de los Test del proyecto
        stage('Run Tests'){
            steps {
                sh "mvn test"
            }
        }

        //Paso donde se realiza el escaneo del proyecto con SonarQube
        //Las propiedades para realizar el analisis las toma del archivo sonar-project.properties
        stage('SonarQube') {
            steps {
                withSonarQubeEnv("sonarqube-container") {
                    //sh "${scannerHome}/bin/sonar-scanner" - LINUX
                    sh "${scannerHome}/bin/sonar-scanner.bat" //WINDOWS
                }
            }
        }

        //Paso para guardar el reporte de covertura de jacoco en Jenkins
        stage('Coverage') {
            steps {
                recordCoverage(tools: [[pattern: '**/jacoco.xml']])
            }
        }

        //Paso donde almacenara de forma temporal en Jenkins los artefactos
        //que se encuentran en todas las carpetas target del proyecto
        stage('Save Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }

        //Paso para publicar los artefactos en el repositorio de dependencias NEXUS
        stage('Publish Artifacts') {
            steps {
                script {
                    pom_parent = readMavenPom file: "pom.xml";
                    pom_common = readMavenPom file: "./common-tools/pom.xml";
                    nexusArtifactUploader (
                        nexusVersion: NEXUS_VERSION,
                        protocol: NEXUS_PROTOCOL,
                        nexusUrl: NEXUS_URL,
                        groupId: pom_parent.groupId,
                        version: pom_parent.version,
                        repository: NEXUS_REPOSITORY,
                        credentialsId: NEXUS_CREDENTIAL_ID,
                        artifacts: [
                            [
                                artifactId: pom_common.artifactId,
                                classifier: '',
                                file: './common-tools/target/' + pom_common.artifactId + '.jar',
                                type: 'jar'
                            ]
                        ]
                    );
                }
            }
        }
    }
}
