
List schema registry subjects

https://github.com/niqdev/kafka-scala-examples#schema-registry

http -v :8081/subjects

# send avsc to schema registry

http -v POST :8081/subjects/<topic>-value/versions \
  Accept:application/vnd.schemaregistry.v1+json \
  schema=@src/main/avro/user.avsc

# list subject's versions

http -v :28081/subjects/<topic>-value/versions