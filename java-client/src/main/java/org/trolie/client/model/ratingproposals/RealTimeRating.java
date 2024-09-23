/*
 *  ===========================================================================
 *
 *  Copyright and Proprietary Information
 *
 *  Copyright (c) 1993, 2024 General Electric Technology GmbH. All Rights Reserved.
 *
 *  NOTE: CONTAINS CONFIDENTIAL AND PROPRIETARY INFORMATION OF GENERAL ELECTRIC
 *  TECHNOLOGY GMBH AND MAY NOT BE REPRODUCED, TRANSMITTED, STORED, OR COPIED IN
 *  WHOLE OR IN PART, OR USED TO FURNISH INFORMATION TO OTHERS, WITHOUT THE PRIOR
 *  WRITTEN PERMISSION OF GENERAL ELECTRIC TECHNOLOGY GMBH OR GRID SOLUTIONS.
 *
 *  ==========================================================================
 */

package org.trolie.client.model.ratingproposals;

import java.util.List;
import java.util.Map;

import org.trolie.client.model.common.InputValue;
import org.trolie.client.model.common.LimitValue;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class RealTimeRating {

    @JsonProperty("resource-id")
    private String resourceId;

    @JsonProperty("continuous-operating-limit")
    private Map<String, Float> continuousOperatingLimit;

    @JsonProperty("emergency-operating-limits")
    private List<LimitValue> emergencyOperatingLimits;

    @JsonProperty("inputs-used")
    private List<InputValue> inputsUsed;
}
