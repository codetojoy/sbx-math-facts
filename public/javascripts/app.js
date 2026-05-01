(function () {
    function ViewModel() {
        var self = this;
        self.input = ko.observable('');
        self.result = ko.observable(null);
        self.error = ko.observable('');
        self.hasResult = ko.pureComputed(function () { return self.result() !== null; });
        self.canSubmit = ko.pureComputed(function () {
            return /^-?\d+$/.test(String(self.input()).trim());
        });

        self.check = function () {
            self.error('');
            self.result(null);
            var n = parseInt(String(self.input()).trim(), 10);
            $.ajax({
                url: '/api/check',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ n: n })
            }).done(function (data) {
                // FIXME STUB: backend does not yet return `palindromic`. Computing it
                // client-side so the UI can display the fact. REMOVE this block once
                // the backend MathFacts DTO includes `palindromic`.
                if (data && data.palindromic === undefined) {
                    var s = String(data.n);
                    data.palindromic = s === s.split('').reverse().join('');
                }
                self.result(data);
            }).fail(function (xhr) {
                self.error('Request failed: ' + xhr.status + ' ' + xhr.statusText);
            });
        };
    }

    $(function () {
        ko.applyBindings(new ViewModel());
    });
})();
