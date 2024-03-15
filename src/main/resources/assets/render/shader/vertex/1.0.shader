#version 400 core

in vec3 vertex_position;
in vec2 vertex_texture_coordinates;
in vec3 vertex_normal;

uniform mat4 camera_projection;
uniform mat4 camera_view;
uniform mat4 transformation;
uniform vec3 light_position;

out vec2 pass_texture_coordinates;
out vec3 surface_normal;
out vec3 to_light_vector;

void main() {
    vec4 world_position = transformation * vec4(vertex_position, 1.0);

    gl_Position = camera_projection * camera_view * world_position;
    pass_texture_coordinates = vertex_texture_coordinates;

    surface_normal = (transformation * vec4(vertex_normal, 0.0)).xyz;
    to_light_vector = light_position - world_position.xyz;
}