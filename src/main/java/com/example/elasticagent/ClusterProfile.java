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

package com.example.elasticagent;

import java.util.HashMap;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClusterProfile {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                                     .excludeFieldsWithoutExposeAnnotation()
                                                     .create();

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("plugin_id")
    private String pluginId;

    @Expose
    @SerializedName("properties")
    private ClusterProfileProperties properties;

    public ClusterProfile(String id, String pluginId, PluginSettings pluginSettings) {
        this.id = id;
        this.pluginId = pluginId;
        setClusterProfileProperties(pluginSettings);
    }

    public static ClusterProfile fromJSON(String json) {
        return GSON.fromJson(json, ClusterProfile.class);
    }

    public String getId() {
        return id;
    }

    public String getPluginId() {
        return pluginId;
    }

    public ClusterProfileProperties getProperties() {
        return properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClusterProfileProperties(PluginSettings pluginSettings) {
        this.properties = ClusterProfileProperties.fromConfiguration(GSON.fromJson(GSON.toJson(pluginSettings), HashMap.class));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClusterProfile that = (ClusterProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pluginId, that.pluginId) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pluginId, properties);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", this.id)
                          .add("pluginId", this.pluginId)
                          .add("properties", this.properties)
                          .toString();
    }

}
