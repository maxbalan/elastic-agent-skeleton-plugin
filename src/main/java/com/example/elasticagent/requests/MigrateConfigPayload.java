/*
 * Copyright 2017 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.elasticagent.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.elasticagent.AgentInstances;
import com.example.elasticagent.ClusterProfile;
import com.example.elasticagent.ElasticAgentProfile;
import com.example.elasticagent.PluginSettings;
import com.example.elasticagent.RequestExecutor;
import com.example.elasticagent.executors.MigrateConfigRequestExecutor;
import com.google.common.base.MoreObjects;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MigrateConfigPayload {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                                     .create();

    @Expose
    @SerializedName("plugin_settings")
    private PluginSettings pluginSettings;

    @Expose
    @SerializedName("elastic_agent_profiles")
    private List<ElasticAgentProfile> elasticAgentProfiles;

    @Expose
    @SerializedName("cluster_profiles")
    private List<ClusterProfile> clusterProfiles;

    public MigrateConfigPayload() {
    }

    public MigrateConfigPayload(PluginSettings pluginSettings,
                                List<ElasticAgentProfile> elasticAgentProfileProperties,
                                List<ClusterProfile> clusterProfiles) {
        this.pluginSettings = pluginSettings;
        this.elasticAgentProfiles = elasticAgentProfileProperties;
        this.clusterProfiles = clusterProfiles;
    }

    public static MigrateConfigPayload fromJSON(String json) {
        return GSON.fromJson(json, MigrateConfigPayload.class);
    }

    public RequestExecutor executor(Map<String, AgentInstances> allAgentInstances) {
        return new MigrateConfigRequestExecutor(this);
    }

    public PluginSettings pluginSettings() {
        return pluginSettings;
    }

    public List<ElasticAgentProfile> elasticAgentProfileProperties() {
        return new ArrayList<>(this.elasticAgentProfiles);
    }

    public List<ClusterProfile> clusterProfiles() {
        return new ArrayList<>(this.clusterProfiles);
    }

    public String toJSON() {
        return GSON.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MigrateConfigPayload that = (MigrateConfigPayload) o;
        return Objects.equals(pluginSettings, that.pluginSettings) &&
                Objects.equals(clusterProfiles, that.clusterProfiles) &&
                Objects.equals(elasticAgentProfiles, that.elasticAgentProfiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pluginSettings, clusterProfiles, elasticAgentProfiles);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("pluginSettings", this.pluginSettings)
                          .add("elasticAgentProfiles", this.elasticAgentProfiles)
                          .add("clusterProfiles", this.clusterProfiles)
                          .toString();
    }

}
