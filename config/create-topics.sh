echo "Aguardando Kafka iniciar..."
sleep 20

kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic pedidos

echo "Tópicos criados com sucesso."
