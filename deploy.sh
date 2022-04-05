#oc new-build java --name=instacapture --binary=true
./gradlew build
oc start-build bc/instacapture --from-file=build/libs/instacapture-0.0.1-SNAPSHOT.jar --follow
oc apply -f oc/deploy.yaml
#oc apply -f oc/cluster-ip.yaml
#oc expose -f oc/cluster-ip.yaml
