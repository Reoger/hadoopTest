package com.hut.bean;

public class IndexSettings {

    /**
     * settings : {"number_of_shards":3,"number_of_replicas":1}
     */
    private SettingsEntity settings;

    public void setSettings(SettingsEntity settings) {
        this.settings = settings;
    }

    public SettingsEntity getSettings() {
        return settings;
    }

    public static class SettingsEntity {
        /**
         * number_of_shards : 3
         * number_of_replicas : 1
         */
        private int number_of_shards;
        private int number_of_replicas;

        public void setNumber_of_shards(int number_of_shards) {
            this.number_of_shards = number_of_shards;
        }

        public void setNumber_of_replicas(int number_of_replicas) {
            this.number_of_replicas = number_of_replicas;
        }

        public int getNumber_of_shards() {
            return number_of_shards;
        }

        public int getNumber_of_replicas() {
            return number_of_replicas;
        }
    }
}
