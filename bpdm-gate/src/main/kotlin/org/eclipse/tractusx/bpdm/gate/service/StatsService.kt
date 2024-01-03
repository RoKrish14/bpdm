/*******************************************************************************
 * Copyright (c) 2021,2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

package org.eclipse.tractusx.bpdm.gate.service

import org.eclipse.tractusx.bpdm.common.model.StageType
import org.eclipse.tractusx.bpdm.gate.api.model.SharingStateType
import org.eclipse.tractusx.bpdm.gate.api.model.response.StatsSharingStatesResponse
import org.eclipse.tractusx.bpdm.gate.api.model.response.StatsStagesResponse
import org.eclipse.tractusx.bpdm.gate.repository.SharingStateRepository
import org.eclipse.tractusx.bpdm.gate.repository.generic.BusinessPartnerRepository
import org.springframework.stereotype.Service

@Service
class StatsService(
    private val sharingStateRepository: SharingStateRepository,
    private val businessPartnerRepository: BusinessPartnerRepository
) {

    fun countSharingStates(): StatsSharingStatesResponse {
        val counts = sharingStateRepository.countSharingStateTypes()
        val countsByType = counts.associate { Pair(it.type, it.count) }

        return StatsSharingStatesResponse(
            initialTotal = countsByType[SharingStateType.Initial] ?: 0,
            pendingTotal = countsByType[SharingStateType.Pending] ?: 0,
            readyTotal = countsByType[SharingStateType.Ready] ?: 0,
            successTotal = countsByType[SharingStateType.Success] ?: 0,
            errorTotal = countsByType[SharingStateType.Error] ?: 0
        )
    }

    fun countBusinessPartnersPerStage(): StatsStagesResponse {
        val counts = businessPartnerRepository.countPerStages()
        val countsByType = counts.associate { Pair(it.stage, it.count) }

        return StatsStagesResponse(
            inputTotal = countsByType[StageType.Input] ?: 0,
            outputTotal = countsByType[StageType.Output] ?: 0
        )

    }


}