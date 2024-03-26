#version 400 core

in vec2 out_vertex_texture_coordinates;
in float out_vertex_ao;

out vec4 out_color;

uniform sampler2D texture_sampler;

void main(void) {
    vec4 vertex_texture_color = texture(texture_sampler, out_vertex_texture_coordinates);
    out_color = vertex_texture_color * vec4(out_vertex_ao, out_vertex_ao, out_vertex_ao, 1);
}