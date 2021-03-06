package com.budjb.rabbitmq.test

import com.budjb.rabbitmq.RabbitContext
import grails.test.mixin.integration.Integration
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import spock.lang.Specification

@Integration
class ReportSpec extends Specification {
    RabbitContext rabbitContext

    def 'Verify that the status report contains the expected information'() {
        setup:
        def expected = [
            [
                consumers   : [
                    [
                        fullName     : 'com.budjb.rabbitmq.test.AllTopicConsumer',
                        load         : 0,
                        name         : 'AllTopicConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'topic-queue-all',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.MessageContextConsumer',
                        load         : 0,
                        name         : 'MessageContextConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'message-context',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.ReportingConsumer',
                        load         : 0,
                        name         : 'ReportingConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'reporting',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.SleepingConsumer',
                        load         : 0,
                        name         : 'SleepingConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'sleeping',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.SpecificTopicConsumer',
                        load         : 0,
                        name         : 'SpecificTopicConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'topic-queue-specific',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.StringConsumer',
                        load         : 0,
                        name         : 'StringConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'string-test',
                        runningState : 'RUNNING'
                    ],
                    [
                        fullName     : 'com.budjb.rabbitmq.test.SubsetTopicConsumer',
                        load         : 0,
                        name         : 'SubsetTopicConsumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'topic-queue-subset',
                        runningState : 'RUNNING'
                    ]
                ],
                host        : 'localhost',
                name        : 'connection1',
                port        : 5672,
                runningState: 'RUNNING',
                virtualHost : 'test1.rabbitmq.budjb.com'
            ],
            [
                consumers   : [
                    [
                        fullName     : 'com.budjb.rabbitmq.test.Connection2Consumer',
                        load         : 0,
                        name         : 'Connection2Consumer',
                        numConfigured: 1,
                        numConsuming : 1,
                        numProcessing: 0,
                        queue        : 'connection2-queue',
                        runningState : 'RUNNING'
                    ]
                ],
                host        : 'localhost',
                name        : 'connection2',
                port        : 5672,
                runningState: 'RUNNING',
                virtualHost : 'test2.rabbitmq.budjb.com'
            ]
        ]

        when:
        def report = new JsonSlurper().parseText(new JsonBuilder(rabbitContext.getStatusReport()).toString())
        report.each {
            it.consumers = it.consumers.sort { it.name }
        }

        then:
        report == expected
    }
}
