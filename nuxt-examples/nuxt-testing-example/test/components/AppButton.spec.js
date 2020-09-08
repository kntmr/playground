import AppButton from '~/components/AppButton.vue'
import { mount } from '@vue/test-utils'

describe('component/AppButton.vue', () => {
  test('snapshot 1', () => {
    const wrapper = mount(AppButton)
    expect(wrapper.element).toMatchSnapshot()
  })
  test('snapshot 2', () => {
    const wrapper = mount(AppButton)
    wrapper.find('button').trigger('click')
    expect(wrapper.element).toMatchSnapshot()
  })
})