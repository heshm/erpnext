/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
angular.module('flowableModeler')
  .controller('DiagramCtrl', ['$rootScope', '$scope', '$translate', '$http', '$location', '$routeParams','$modal', '$popover', '$timeout', 'appResourceRoot', 'ResourceService',
                              function ($rootScope, $scope, $translate, $http, $location, $routeParams, $modal, $popover, $timeout, appResourceRoot, ResourceService) {
	$rootScope.setMainPageById('processes');
	
    $scope.showDiagram = function() {
    	$timeout(function() {
            if($routeParams.processStarted === 'true') {
            	jQuery("#bpmnModel").attr('data-model-type', 'runtime');
            	jQuery("#bpmnModel").attr('data-model-id', $routeParams.processInstanceId);
            }else {
            	jQuery("#bpmnModel").attr('data-model-type', 'process-definition');
            	jQuery("#bpmnModel").attr('data-process-definition-id', $routeParams.processInstanceId);
            }

            // in case we want to show a historic model, include additional attribute on the div
            /*
              if(!$scope.model.process.latestVersion) {
                jQuery("#bpmnModel").attr('data-history-id', $routeParams.processModelHistoryId);
              }
            */
            var viewerUrl = appResourceRoot + "./display/displaymodel.html?version=" + Date.now();

            // If Activiti has been deployed inside an AMD environment Raphael will fail to register
            // itself globally until displaymodel.js (which depends ona global Raphale variable) is runned,
            // therefor remove AMD's define method until we have loaded in Raphael and displaymodel.js
            // and assume/hope its not used during.
            var amdDefine = window.define;
            window.define = undefined;
            ResourceService.loadFromHtml(viewerUrl, function(){
                // Restore AMD's define method again
                window.define = amdDefine;
            });
        }, 100);
    };
    
    $scope.showDiagram();
}]);
