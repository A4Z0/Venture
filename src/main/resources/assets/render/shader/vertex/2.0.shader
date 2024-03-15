#version 400 core

in vec3 vertex_position;
in vec2 vertex_texture_coordinates;
in vec3 vertex_normal;

uniform mat4 camera_projection;
uniform mat4 camera_view;
uniform mat4 transformation;

out vec2 out_vertex_texture_coordinates;

void main() {
    gl_Position = camera_projection * camera_view * transformation * vec4(vertex_position, 1.0);
    out_vertex_texture_coordinates = vertex_texture_coordinates;
}