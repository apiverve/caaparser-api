// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     CAARecordParserData data = Converter.fromJsonString(jsonString);

package com.apiverve.caaparser.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static CAARecordParserData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(CAARecordParserData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(CAARecordParserData.class);
        writer = mapper.writerFor(CAARecordParserData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// CAARecordParserData.java

package com.apiverve.caaparser.data;

import com.fasterxml.jackson.annotation.*;

public class CAARecordParserData {
    private String rawRecord;
    private Parsed parsed;
    private CAInfo caInfo;
    private Interpretation interpretation;
    private String tagDescription;
    private boolean isValid;

    @JsonProperty("raw_record")
    public String getRawRecord() { return rawRecord; }
    @JsonProperty("raw_record")
    public void setRawRecord(String value) { this.rawRecord = value; }

    @JsonProperty("parsed")
    public Parsed getParsed() { return parsed; }
    @JsonProperty("parsed")
    public void setParsed(Parsed value) { this.parsed = value; }

    @JsonProperty("ca_info")
    public CAInfo getCAInfo() { return caInfo; }
    @JsonProperty("ca_info")
    public void setCAInfo(CAInfo value) { this.caInfo = value; }

    @JsonProperty("interpretation")
    public Interpretation getInterpretation() { return interpretation; }
    @JsonProperty("interpretation")
    public void setInterpretation(Interpretation value) { this.interpretation = value; }

    @JsonProperty("tag_description")
    public String getTagDescription() { return tagDescription; }
    @JsonProperty("tag_description")
    public void setTagDescription(String value) { this.tagDescription = value; }

    @JsonProperty("is_valid")
    public boolean getIsValid() { return isValid; }
    @JsonProperty("is_valid")
    public void setIsValid(boolean value) { this.isValid = value; }
}

// CAInfo.java

package com.apiverve.caaparser.data;

import com.fasterxml.jackson.annotation.*;

public class CAInfo {
    private String name;
    private String type;
    private boolean wildcardSupport;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("wildcard_support")
    public boolean getWildcardSupport() { return wildcardSupport; }
    @JsonProperty("wildcard_support")
    public void setWildcardSupport(boolean value) { this.wildcardSupport = value; }
}

// Interpretation.java

package com.apiverve.caaparser.data;

import com.fasterxml.jackson.annotation.*;

public class Interpretation {
    private String meaning;
    private String restriction;
    private boolean critical;
    private String criticalExplanation;

    @JsonProperty("meaning")
    public String getMeaning() { return meaning; }
    @JsonProperty("meaning")
    public void setMeaning(String value) { this.meaning = value; }

    @JsonProperty("restriction")
    public String getRestriction() { return restriction; }
    @JsonProperty("restriction")
    public void setRestriction(String value) { this.restriction = value; }

    @JsonProperty("critical")
    public boolean getCritical() { return critical; }
    @JsonProperty("critical")
    public void setCritical(boolean value) { this.critical = value; }

    @JsonProperty("critical_explanation")
    public String getCriticalExplanation() { return criticalExplanation; }
    @JsonProperty("critical_explanation")
    public void setCriticalExplanation(String value) { this.criticalExplanation = value; }
}

// Parsed.java

package com.apiverve.caaparser.data;

import com.fasterxml.jackson.annotation.*;

public class Parsed {
    private String domain;
    private long ttl;
    private String parsedClass;
    private long flags;
    private String tag;
    private String value;

    @JsonProperty("domain")
    public String getDomain() { return domain; }
    @JsonProperty("domain")
    public void setDomain(String value) { this.domain = value; }

    @JsonProperty("ttl")
    public long getTTL() { return ttl; }
    @JsonProperty("ttl")
    public void setTTL(long value) { this.ttl = value; }

    @JsonProperty("class")
    public String getParsedClass() { return parsedClass; }
    @JsonProperty("class")
    public void setParsedClass(String value) { this.parsedClass = value; }

    @JsonProperty("flags")
    public long getFlags() { return flags; }
    @JsonProperty("flags")
    public void setFlags(long value) { this.flags = value; }

    @JsonProperty("tag")
    public String getTag() { return tag; }
    @JsonProperty("tag")
    public void setTag(String value) { this.tag = value; }

    @JsonProperty("value")
    public String getValue() { return value; }
    @JsonProperty("value")
    public void setValue(String value) { this.value = value; }
}