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

package org.eclipse.tractusx.bpdm.orchestrator.model

import org.eclipse.tractusx.orchestrator.api.model.*
import java.time.Instant

data class TaskProcessingState(
    val mode: TaskMode,
    var resultState: ResultState,
    var errors: List<TaskErrorDto> = emptyList(),

    var step: TaskStep,
    var stepState: StepState,
    var reservationTimeout: Instant?,

    val taskCreatedAt: Instant,
    var taskModifiedAt: Instant,
    val taskTimeout: Instant,
)