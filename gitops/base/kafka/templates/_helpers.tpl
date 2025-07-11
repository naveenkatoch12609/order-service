{{- define "kafka.name" -}}
kafka
{{- end }}

{{- define "kafka.fullname" -}}
{{ include "kafka.name" . }}-svc
{{- end }}
