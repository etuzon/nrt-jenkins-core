package nrt.jenkins.groovy.utils.jenkins

import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.Domain


class JenkinsCredsUtil {
    static getCredsById(String credsId) {
        def credsStore = Jenkins.instance.getExtensionList(
                com.cloudbees.plugins.credentials.SystemCredentialsProvider
        )[0]?.getStore()

        if (credsStore) {
            def credsList = credsStore?.getCredentials(Domain.global())
            def creds = credsList.find { it.id == credsId }
            return creds
        }
        return null
    }
}
