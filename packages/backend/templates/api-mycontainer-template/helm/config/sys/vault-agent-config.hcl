# Use when running with envconsul
# kubectl create cm vault-agent-envconsul-config --from-file ./config

# Comment this out if running as sidecar instead of initContainer
exit_after_auth = true

pid_file = "/home/vault/pidfile"

auto_auth {
    method "kubernetes" {
        mount_path = "auth/k8s/digital_web_mobile_stg"
        config = {
            role = "stg_ukd_services_enterprise_integration"
        }
    }

    sink "file" {
        config = {
            path = "/home/vault/.vault-token"
            mode = 0755
        }
    }
}
template {
destination = "/vault/secrets/vault.yaml"
contents = <<EOT
{{- with secret "team_digital_integration_generic/microservice/pension/sys" }}
secret:
    field: {{ index .Data "secret.field" }}
{{ end }}
EOT
}
