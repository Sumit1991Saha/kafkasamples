public enum Topics {

    TOPIC_SIMPLE("simple", 1, 1),
    TOPIC_ONLY_REPLICATION("only_replication",3,1),
    TOPIC_ONLY_PARTITION("only_partition", 1,3),
    TOPIC_BOTH_REPLICATION_AND_PARTITION("both_replication_partition",3,3);

    private String topicName;
    private int replicationFactor;
    private int noOfPartitions;

    Topics(String topicName, int replicationFactor, int noOfPartitions) {
        this.topicName = topicName;
        this.replicationFactor = replicationFactor;
        this.noOfPartitions = noOfPartitions;
    }

    public String getTopicName() {
        return this.topicName;
    }
}
