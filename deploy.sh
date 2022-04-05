oc new-build java --name=instacapture --binary=true
oc start-build bc/instacapture --from-file=build/libs/instacapture-0.0.1-SNAPSHOT.jar --follow
oc run pod-2 --image image-registry.openshift-image-registry.svc:5000/rishhabhhanday-dev/instacapture:latest
