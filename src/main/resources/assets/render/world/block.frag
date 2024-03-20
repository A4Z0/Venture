#version 400 core

in vec2 out_vertex_texture_coordinates;
in vec4 out_vertex_ao;

out vec4 out_color;

uniform sampler2D texture_sampler;

const float gamma_factor = 2.0;

void main(void) {
    vec4 vertex_texture_color = texture(texture_sampler, out_vertex_texture_coordinates);
    float gamma_correction = pow(out_vertex_ao.w, gamma_factor);
    out_color = vertex_texture_color * gamma_correction;
}
