<template>
    <div>
        <div id="container"></div>
    </div>
</template>
<script>
 import * as Three from 'three'

export default ({
    name:'ThreeIndex',
    data(){
        return {
            camera: null,
            scene: null,
            renderer: null,
            mesh: null,
        }
    },
    methods:{
        init(){
        let container = document.getElementById("container");
        this.camera = new Three.PerspectiveCamera(
        70,
        container.clientWidth / container.clientHeight,0.01,1000);
        this.camera.position.z = 0.6;
        this.scene = new Three.Scene();
        let geometry = new Three.CylinderBufferGeometry(0.2, 0.2, 0.2);
        let material = new Three.MeshNormalMaterial();
        this.mesh = new Three.Mesh(geometry, material);
        this.scene.add(this.mesh);
        this.renderer = new Three.WebGLRenderer({ antialias: true });
        this.renderer.setSize(container.clientWidth, container.clientHeight);
        container.appendChild(this.renderer.domElement);
        },
        animate() {
            requestAnimationFrame(this.animate);
            this.mesh.rotation.x += 0.01;
            this.mesh.rotation.y += 0.02;
            this.renderer.render(this.scene, this.camera);
        },
    },
    mounted(){
        this.init();
        this.animate();
    }
})
</script>
<style scoped>

#container {

  height: 400px;

}
</style>