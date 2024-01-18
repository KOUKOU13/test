import {ref} from 'vue'

const active = ref(false);

export function activateModal() {
    return {
        active,
        activateModal: () => active.value = true,
        deactivateModal: () => active.value = false
    };
}
