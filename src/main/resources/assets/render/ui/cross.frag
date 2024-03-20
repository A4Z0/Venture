#version 400 core

out vec4 out_color;

void main() {
    out_color = vec4(1.0 - gl_FragCoord.x / 1280.0, 1.0 - gl_FragCoord.y / 720.0, 1.0, 1.0);
}